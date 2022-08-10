package com.mycompany;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mycompany.models.enums.Choice;
import com.mycompany.payload.request.LoginRequest;
import com.mycompany.payload.request.RoundPlayRequest;
import com.mycompany.payload.request.SignupRequest;
import com.mycompany.payload.response.RankingResponse;
import com.mycompany.security.jwt.JwtUtils;
import com.mycompany.security.services.UserDetailsImpl;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class GameControllerTest {

	RestTemplate testRestTemplate = new RestTemplate();

	@LocalServerPort
	int randomServerPort;
	
	@Autowired
	JwtUtils jwtUtils;

	@Test
	@Order(1)
	void teste_acesso_nao_autorizado() throws URISyntaxException {

		Exception exception = assertThrows(Exception.class, () -> {

			URI uri = new URI("http://localhost:" + randomServerPort + "/api/game/ranking");
			testRestTemplate.getForEntity(uri, RankingResponse.class);

		});

		String expectedMessage = "NÃO AUTORIZADO";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	/**
	 * IMPORTANTE:
	 * 
	 * Por usar JWT em Cookie e BD em memória, faz-se necessário, para acessar rotas
	 * com segurança:
	 * 
	 *   1. Registrar novo jogador 
	 *   2. Fazer LOGIN 
	 *   3. Injetar COOKIE no REQUEST
	 * 
	 */
	@Test
	@Order(2)
	void teste_iniciar_game() throws URISyntaxException {

		// REGISTRO
		URI uri = new URI("http://localhost:" + randomServerPort + "/api/auth/signup");
		SignupRequest signupRequest = new SignupRequest();
		signupRequest.setUsername("Jogador 1000");
		signupRequest.setPassword("123456");
		testRestTemplate.postForEntity(uri, signupRequest, String.class);

		
		// LOGIN
		uri = new URI("http://localhost:" + randomServerPort + "/api/auth/signin");
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("Jogador 1000");
		loginRequest.setPassword("123456");
		testRestTemplate.postForEntity(uri, loginRequest, String.class);

		// COOKIE
		HttpHeaders headers = new HttpHeaders();
		
		String cookie = jwtUtils.generateJwtCookie(new UserDetailsImpl("Jogador 1000")).toString();
		
		headers.add( "Cookie", cookie);

		// INICAR GAME
		uri = new URI("http://localhost:" + randomServerPort + "/api/game/start");
		ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.POST,
				new HttpEntity<String>(headers), String.class);

		String expectedMessage = "Game Iniciado!";
		String actualMessage = response.getBody();

		assertTrue(actualMessage.contains(expectedMessage));

		
		//ITERAR  
		for (int i = 1; i <=10 ; i++) {
			
		 		
				// PEGAR QUIZZ DA RODADA
				uri = new URI("http://localhost:" + randomServerPort + "/api/game/play");
				response = testRestTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<String>(headers), String.class);
		
				expectedMessage = "roundID";
				actualMessage = response.getBody();
		
				assertTrue(actualMessage.contains(expectedMessage));
		
				
				// ENVIAR RESPOSTA DA RODADA
				RoundPlayRequest roundPlayRequest = new RoundPlayRequest();
				roundPlayRequest.setRound(1L);
				roundPlayRequest.setChoice(Choice.B);
				
				HttpEntity<RoundPlayRequest> requestEntity = new HttpEntity<RoundPlayRequest>(roundPlayRequest, headers);
		
				uri = new URI("http://localhost:" + randomServerPort + "/api/game/play");
				response = testRestTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
				
				expectedMessage = "acertou";
				actualMessage = response.getBody();
		
				assertTrue(actualMessage.contains(expectedMessage));
		
		}
		
		
		// FINALIZAR GAME
		uri = new URI("http://localhost:" + randomServerPort + "/api/game/finish");
		response = testRestTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<String>(headers), String.class);

		expectedMessage = "Game Finalizado!";
		actualMessage = response.getBody();

		assertTrue(actualMessage.contains(expectedMessage));
		
	 
		
		// RANKING
		uri = new URI("http://localhost:" + randomServerPort + "/api/game/ranking");
		
		ResponseEntity<RankingResponse> res = testRestTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<String>(headers), RankingResponse.class);

		expectedMessage = "Jogador 1000";
		actualMessage = res.getBody().getRanking().get(0).getNome();

		assertTrue(actualMessage.contains(expectedMessage));
		
		

	}

}
