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

 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AboutControllerTest {

	RestTemplate testRestTemplate = new RestTemplate();

	@LocalServerPort
	int randomServerPort;

	@Test
	void teste_about_end_point() throws URISyntaxException {
		URI uri = new URI("http://localhost:" + randomServerPort + "/api/info/about");
 
		ResponseEntity<String> response = testRestTemplate.getForEntity(uri, String.class);

		String expectedMessage = "API REST";
		String actualMessage =response.getBody();

		assertTrue(actualMessage.contains(expectedMessage));

	}

}
