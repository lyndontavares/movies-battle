package com.mycompany.models.dto;

public class MovieItem {
	
	@Override
	public String toString() {
		return "MovieItem [imdbID=" + imdbID + "]";
	}

	private String imdbID;

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	
}
