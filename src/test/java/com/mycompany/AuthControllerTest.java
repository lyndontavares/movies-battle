package com.mycompany;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mycompany.payload.request.SignupRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {

	RestTemplate testRestTemplate = new RestTemplate();

	@LocalServerPort
	int randomServerPort;

	@Test
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

}