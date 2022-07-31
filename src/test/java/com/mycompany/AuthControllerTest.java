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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mycompany.payload.request.LoginRequest;
import com.mycompany.payload.request.SignupRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class AuthControllerTest {

	RestTemplate testRestTemplate = new RestTemplate();

	@LocalServerPort
	int randomServerPort;

	@Test
	@Order(1) 
	void teste_registrar_novojogador() throws URISyntaxException {
 

		URI uri = new URI("http://localhost:" + randomServerPort + "/api/auth/signup");
		SignupRequest request = new SignupRequest();
		request.setUsername("Jogador 1");
		request.setPassword("123456");
		ResponseEntity<String> response = testRestTemplate.postForEntity(uri, request, String.class);

		String expectedMessage = "Jogador 1 registrado com sucesso!";
		String actualMessage =response.getBody();

		assertTrue(actualMessage.contains(expectedMessage));

	}
	
	@Test
	@Order(2) 
	void teste_login_usuario() throws URISyntaxException {
 

		URI uri = new URI("http://localhost:" + randomServerPort + "/api/auth/signin");
		LoginRequest request = new LoginRequest();
		request.setUsername("Jogador 1");
		request.setPassword("123456");
		ResponseEntity<String> response = testRestTemplate.postForEntity(uri, request, String.class);

		String expectedMessage = "Jogador 1";
		String actualMessage =response.getBody();

		assertTrue(actualMessage.contains(expectedMessage));

	}
	
	
	@Test
	@Order(3) 
	void teste_logout() throws URISyntaxException {
 
		//REGISTRO
		URI uri = new URI("http://localhost:" + randomServerPort + "/api/auth/signup");
		SignupRequest signupRequest = new SignupRequest();
		signupRequest.setUsername("Jogador 2");
		signupRequest.setPassword("123456");
		testRestTemplate.postForEntity(uri, signupRequest, String.class);
		
		//LOGIN
		uri = new URI("http://localhost:" + randomServerPort + "/api/auth/signin");
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("Jogador 2");
		loginRequest.setPassword("123456");
		testRestTemplate.postForEntity(uri, loginRequest, String.class);

		//LOGOUT
		uri = new URI("http://localhost:" + randomServerPort + "/api/auth/signout");
 		ResponseEntity<String> response = testRestTemplate.postForEntity(uri,null,String.class);
		String expectedMessage = "Você foi desconectado!";
		String actualMessage =response.getBody();

		assertTrue(actualMessage.contains(expectedMessage));

	}
	
	@Test
	@Order(4) 
	void teste_excecao_registrar_jogador_ja_cadastrado() throws URISyntaxException {
 		
		Exception exception = assertThrows(Exception.class, () -> {
			
			URI uri = new URI("http://localhost:" + randomServerPort + "/api/auth/signup");
			SignupRequest request = new SignupRequest();
			request.setUsername("Jogador 1");
			request.setPassword("123456");
			testRestTemplate.postForEntity(uri, request, String.class);
	
		});

		String expectedMessage = "Nome já cadastrado!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

}
