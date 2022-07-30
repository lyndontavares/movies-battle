package com.mycompany.payload.request;

import com.mycompany.models.enums.Choice;

public class RoundRequest {

	private Long round;
	private Choice choice;

	public Long getRound() {
		return round;
	}

	public void setRound(Long round) {
		this.round = round;
	}

	public Choice getChoice() {
		return choice;
	}

	public void setChoice(Choice choice) {
		this.choice = choice;
	}

}
