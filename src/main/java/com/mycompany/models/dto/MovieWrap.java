package com.mycompany.models.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieWrap {
	
	@JsonProperty("Search")
	private List<MovieWrapItem> search;
	
	private int totalResults;

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public List<MovieWrapItem> getSearch() {
		return search;
	}

	public void setSearch(List<MovieWrapItem> search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return "MovieWrap [search=" + search + ", totalResults=" + totalResults + "]";
	}

}
