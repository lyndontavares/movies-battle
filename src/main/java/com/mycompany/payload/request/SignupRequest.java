package com.mycompany.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
 
public class SignupRequest {
	
    @NotBlank
    @Size(min = 3, max = 20)
    @ApiModelProperty(example = "Jogador 1")
    private String username;
    
    @NotBlank
    @Size(min = 6, max = 40)
    @ApiModelProperty(example = "123456",notes = "Mínimo 3, máximo 20")
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

	public SignupRequest() {
		super();
	}
    
    
  
}
