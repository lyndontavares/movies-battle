package com.mycompany.payload.request;

import org.springframework.lang.NonNull;

import com.mycompany.models.enums.Choice;

public class RoundPlayRequest {

	@NonNull
	private Long round;
	
	@NonNull
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
