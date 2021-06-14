package com.springboot.model;

public class MovieDetails {

	public String id;
	public String title;
	public String overview;

	public MovieDetails() {

	}

	public MovieDetails(String movieId, String title, String overview) {
		super();
		this.id = movieId;
		this.title = title;
		this.overview = overview;
	}

	public String getId() {
		return id;
	}

	public void setId(String movieId) {
		this.id = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverView() {
		return overview;
	}

	public void setOverView(String overview) {
		this.overview = overview;
	}

}
