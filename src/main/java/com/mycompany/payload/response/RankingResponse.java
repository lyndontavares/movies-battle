package com.mycompany.payload.response;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.models.dto.Ranking;

public class RankingResponse {
	
	private List<Ranking> ranking;

	public List<Ranking> getRanking() {
		return ranking;
	}

	public void setRanking(List<Ranking> ranking) {
		this.ranking = ranking;
	}

	public RankingResponse( ) {
		ranking = new ArrayList<>();
	}

}
