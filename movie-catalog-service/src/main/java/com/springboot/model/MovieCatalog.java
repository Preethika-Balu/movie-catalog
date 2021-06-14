package com.springboot.model;

public class MovieCatalog {

	String movieId;
	String movieTitle;
	String movieDesc;
	float rating;

	public MovieCatalog() {

	}

	public MovieCatalog(String movieId, String movieTitle, String movieDesc, float rating) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieDesc = movieDesc;
		this.rating = rating;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieDesc() {
		return movieDesc;
	}

	public void setMovieDesc(String movieDesc) {
		this.movieDesc = movieDesc;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

}
