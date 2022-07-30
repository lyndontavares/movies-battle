package com.mycompany.payload.request;

import com.mycompany.models.dto.MovieRound;

public class RoundReadResponse {

	private Long roundID;
	
	private MovieRound movieRound;

	public Long getRoundID() {
		return roundID;
	}

	public void setRoundID(Long roundID) {
		this.roundID = roundID;
	}

	public MovieRound getMovieRound() {
		return movieRound;
	}

	public void setMovieRound(MovieRound movieRound) {
		this.movieRound = movieRound;
	}
	
	
}
