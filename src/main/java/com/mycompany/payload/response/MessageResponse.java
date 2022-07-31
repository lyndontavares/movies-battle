package com.mycompany.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse {
	
	@JsonProperty 
	private String message;

	
	public MessageResponse(String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
