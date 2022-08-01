package com.mycompany;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mycompany.payload.request.LoginRequest;
import com.mycompany.payload.request.SignupRequest;
import com.mycompany.payload.response.RankingResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class GameControllerTest {

	RestTemplate testRestTemplate = new RestTemplate();

	@LocalServerPort
	int randomServerPort;

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
	 * 1. Registrar novo jogador 2. Fazer LOGIN 3. Injetar COOKIE no REQUEST
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
		headers.add("Cookie",
				"mycompany=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb2dhZG9yIDIiLCJpYXQiOjE2NTkzMTA2NTIsImV4cCI6MTY1OTM5NzA1Mn0.yqrCajP8EwEHf-OQPQwcoJNCFw6zcCu8whGNHj1k-hviK5o4Lr_jDwnXXI2z-N-tjijtv_A8WaP8YxDlY6oLmQ; Path=/api; Max-Age=86400; Expires=Mon, 01 Aug 2022 23:39:22 GMT; HttpOnly");

		// INICAR GAME
		uri = new URI("http://localhost:" + randomServerPort + "/api/game/start");
		ResponseEntity<String> response = testRestTemplate.exchange(uri, HttpMethod.POST,
				new HttpEntity<String>(headers), String.class);

		String expectedMessage = "Game Iniciado!";
		String actualMessage = response.getBody();

		assertTrue(actualMessage.contains(expectedMessage));

		// FINALIZAR GAME
		uri = new URI("http://localhost:" + randomServerPort + "/api/game/finish");
		response = testRestTemplate.exchange(uri, HttpMethod.POST,
				new HttpEntity<String>(headers), String.class);

		expectedMessage = "Game Finalizado!";
		actualMessage = response.getBody();

		assertTrue(actualMessage.contains(expectedMessage));

	}

}
