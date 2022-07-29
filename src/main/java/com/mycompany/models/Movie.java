package com.mycompany.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {

	@JsonProperty("Title")
	private String title; // título

	@JsonProperty("Plot")
	private String plot; // resumo

	@JsonProperty("Year")
	private int year; // ano

	private String imdbID; // código

	private String imdbVotes; // total de votos

	private Double imdbRating; // nota

	private Double pontuacao; // total de votos * nota

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public Double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(Double imdbRating) {
		this.imdbRating = imdbRating;
	}

	/**
	 * REQUISITO.3
	 * 
	 * @apiNote Casting para Double no atruito imdbVotes
	 */
	public Double getPontuacao() {
		return imdbRating * Double.valueOf(imdbVotes.replace(",", ""));
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", plot=" + plot + ", year=" + year + ", imdbID=" + imdbID + ", imdbVotes="
				+ imdbVotes + ", imdbRating=" + imdbRating + ", pontuacao=" + pontuacao + "]";
	}

}
