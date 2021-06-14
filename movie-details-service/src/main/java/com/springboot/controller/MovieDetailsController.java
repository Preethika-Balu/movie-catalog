package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.model.MovieDetails;

@RestController
@RequestMapping("/details")
public class MovieDetailsController {

	@Autowired
	RestTemplate restTemplate;

	@Value("${api.key}")
	private String apiKey;


	@RequestMapping("/{movieId}")
	public MovieDetails getData(@PathVariable String movieId) {
		return restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey,
				MovieDetails.class);
//		return new MovieDetails(movieId, movieId + "-desc");
	}
}
