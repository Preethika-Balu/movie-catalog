package com.springboot.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.model.MovieRating;
import com.springboot.model.RatingList;

@Service
public class MovieRatingService {

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "callRatingsFalbackMethod")
	public RatingList getRatingList(String userId) {
		RatingList ratingList = restTemplate.getForObject("http://movie-rating-service/rating/user/" + userId,
				RatingList.class);
		return ratingList;
	}

	public RatingList callRatingsFalbackMethod(String userId) {
		RatingList rl = new RatingList();
		rl.setRatingList(Arrays.asList(new MovieRating(userId, 0)));
		return rl;
	}

}
