package com.springboot.model;

public class MovieRating {

	String movieId;
	float rating;

	public MovieRating() {
	}

	public MovieRating(String movieId, float rating) {
		super();
		this.movieId = movieId;
		this.rating = rating;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
}
