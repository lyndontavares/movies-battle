package com.mycompany.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/game")
public class GameController {
	
	  @GetMapping("/init")
	  public String init() {
	    return "Game iniciado";
	  }
	  
	
}
