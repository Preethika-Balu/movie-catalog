package com.springboot.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.MovieRating;
import com.springboot.model.RatingList;

@RestController
@RequestMapping("/rating")
public class MovieRatingController {

	@RequestMapping("/{movieId}")
	public MovieRating getData(@PathVariable String movieId) {
		return new MovieRating(movieId, 5);
	}

	@RequestMapping("/user/{userId}")
	public RatingList getUserMovieRating(@PathVariable String userId) {
		RatingList rl = new RatingList();
		rl.setRatingList(Arrays.asList(new MovieRating("123", 4), new MovieRating("124", 8)));
		return rl;
	}

}
