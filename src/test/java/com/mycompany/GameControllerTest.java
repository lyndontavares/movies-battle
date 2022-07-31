package com.mycompany;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.mycompany.payload.response.RankingResponse;

 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GameControllerTest {

	RestTemplate testRestTemplate = new RestTemplate();

	@LocalServerPort
	int randomServerPort;

	@Test
	void teste_acesso_nao_autorizado() throws URISyntaxException {

		
		Exception exception = assertThrows(Exception.class, () -> {
			
			URI uri = new URI("http://localhost:" + randomServerPort + "/api/game/ranking");
	 		testRestTemplate.getForEntity(uri, RankingResponse.class);
	
		});

		String expectedMessage = "NÃO AUTORIZADO";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

 

	}

}
