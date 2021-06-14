package com.springboot.model;

import java.util.List;

public class RatingList {

	List<MovieRating> ratingList;

	public RatingList() {
	}

	public List<MovieRating> getRatingList() {
		return ratingList;
	}

	public void setRatingList(List<MovieRating> ratingList) {
		this.ratingList = ratingList;
	}

}
