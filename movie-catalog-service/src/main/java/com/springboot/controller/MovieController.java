package com.springboot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.DiscoveryClient;
import com.springboot.model.MovieCatalog;
import com.springboot.model.MovieDetails;
import com.springboot.model.RatingList;
import com.springboot.service.MovieDetailService;
import com.springboot.service.MovieRatingService;

@RestController
@RequestMapping("/catalog")
@EnableCircuitBreaker
public class MovieController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	MovieDetailService details;

	@Autowired
	MovieRatingService rating;

	DiscoveryClient dc;

	/*
	 * @RequestMapping("/{userId}")
	 * 
	 * @HystrixCommand(fallbackMethod = "callFalbackMethod") public
	 * List<MovieCatalog> getData(@PathVariable String userId) { // RatingList
	 * ratingList = restTemplate.getForObject("http://localhost:8083/rating/user/" +
	 * userId, // RatingList.class); RatingList ratingList =
	 * restTemplate.getForObject("http://movie-rating-service/rating/user/" +
	 * userId, RatingList.class);// Replacing this with service name so that eureka
	 * server can discover the // service List<MovieCatalog> movieCatalogList =
	 * ratingList.getRatingList().stream().map(rating->{ String mId =
	 * rating.getMovieId(); // MovieDetails md =
	 * restTemplate.getForObject("http://localhost:8082/details/" + mId, //
	 * MovieDetails.class); MovieDetails md =
	 * restTemplate.getForObject("http://movie-details-service/details/" + mId,
	 * MovieDetails.class);//// Replacing this with service name so that eureka
	 * server can discover the service return new MovieCatalog(mId, md.getTitle(),
	 * md.getOverView(), rating.getRating()); }).collect(Collectors.toList());
	 * 
	 * return movieCatalogList; }
	 */

	/**
	 * trying to implement circuit breaker by placing api in services for
	 * granularity so that even if 1 service is down other will be called in above
	 * commented method,if 1 fails then other api data will also not be displayed
	 * 
	 */
	@RequestMapping("/{userId}")
//	@HystrixCommand(fallbackMethod = "callFalbackMethod")
	public List<MovieCatalog> getData(@PathVariable String userId) {
		RatingList ratingList = rating.getRatingList(userId);
		List<MovieCatalog> movieCatalogList = ratingList.getRatingList().stream().map(rating->{
			String mId = rating.getMovieId();
			MovieDetails md = details.getMovieDetails(mId);
			return new MovieCatalog(mId, md.getTitle(), md.getOverView(), rating.getRating());
		}).collect(Collectors.toList());
				
		return movieCatalogList;
	}

//	@HystrixCommand(fallbackMethod = "callDetailsFallbackMethod")
//	public MovieDetails getMovieDetails(String mId) {
//		MovieDetails md = restTemplate.getForObject("http://movie-details-service/details/" + mId, MovieDetails.class);
//		return md;
//	}
//
//	public MovieDetails callDetailsFallbackMethod(String mId) {
//		return new MovieDetails(mId, "No data", "No data");
//	}

//	@HystrixCommand(fallbackMethod = "callRatingsFalbackMethod")
//	public RatingList getRatingList(String userId) {
//		RatingList ratingList = restTemplate.getForObject("http://movie-rating-service/rating/user/" + userId,
//				RatingList.class);
//		return ratingList;
//	}
//
//	public RatingList callRatingsFalbackMethod(String userId) {
//		RatingList rl = new RatingList();
//		rl.setRatingList(Arrays.asList(new MovieRating(userId, 0)));
//		return rl;
//	}

	/**
	 * not required if we use seperate fall back for each api
	 */
//	public List<MovieCatalog> callFalbackMethod(@PathVariable String userId) {
//		return Arrays.asList(new MovieCatalog("", "No Movie", "", 0));
//	}
}
