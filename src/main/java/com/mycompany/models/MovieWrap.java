package com.mycompany.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieWrap {
	
	@JsonProperty("Search")
	private List<MovieItem> search;
	
	private int totalResults;

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public List<MovieItem> getSearch() {
		return search;
	}

	public void setSearch(List<MovieItem> search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return "MovieWrap [search=" + search + ", totalResults=" + totalResults + "]";
	}

}
