package com.mycompany.payload.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class LoginRequest {
	
	@ApiModelProperty(example = "Jogador 1")
	@NotBlank
	private String username;

	@ApiModelProperty(example = "123456")
	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
