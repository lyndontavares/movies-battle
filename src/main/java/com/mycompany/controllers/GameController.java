package com.mycompany.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.game.services.GameServiceImpl;
import com.mycompany.models.User;
import com.mycompany.payload.response.MessageResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/game")
public class GameController {
	@Autowired
	private GameServiceImpl gameService;

	@ApiOperation(value = "Finalizar o MovieGame")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna Game finalizado"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@PostMapping("/finalizar")
	public ResponseEntity<?> finalizarGame(HttpServletRequest request) {
		User user = gameService.getUser(request);
		
		return ResponseEntity.ok(new MessageResponse(String.format("%s, Game Finalizado!",user.getUsername())));
	}

	@ApiOperation(value = "Iniciar Game")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna Game iniciado"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@PostMapping("/iniciar")
	public ResponseEntity<?> iniciarrGame(HttpServletRequest request) {

		User user = gameService.getUser(request);
		
		return ResponseEntity.ok(new MessageResponse(String.format("%s, Game Iniciado!",user.getUsername())));
	}

	@ApiOperation(value = "Game Ranking")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna Game Ranking"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@PostMapping("/ranking")
	public ResponseEntity<?> ranking() {
		
		return ResponseEntity.ok(gameService.ranking());
	}

	
	
}
