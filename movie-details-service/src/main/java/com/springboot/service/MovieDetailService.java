package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.model.MovieDetails;

@Service
public class MovieDetailService {

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "callDetailsFallbackMethod")
	public MovieDetails getMovieDetails(String mId) {
		MovieDetails md = restTemplate.getForObject("http://movie-details-service/details/" + mId, MovieDetails.class);
		return md;
	}

	public MovieDetails callDetailsFallbackMethod(String mId) {
		return new MovieDetails(mId, "No data", "No data");
	}

}
