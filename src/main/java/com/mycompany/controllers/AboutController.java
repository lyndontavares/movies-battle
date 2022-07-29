package com.mycompany.controllers;

import java.nio.charset.StandardCharsets;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/info")
public class AboutController {

  @GetMapping("/about")
  public String allAccess() {

	  String string = "API REST para uma aplicação ao estilo card game, onde serão informados dois filmes e o jogador deve acertar aquele que possui melhor avaliação no IMDB";
	  byte[] sBytes = string.getBytes();
	  String asciiEncodedString = new String(sBytes, StandardCharsets.ISO_8859_1);
	  return asciiEncodedString;
  }
  
}
