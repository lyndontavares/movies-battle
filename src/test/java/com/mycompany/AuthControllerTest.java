package com.mycompany;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.mycompany.payload.request.SignupRequest;
import com.mycompany.payload.response.MessageResponse;

 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {

	RestTemplate testRestTemplate = new RestTemplate();

	@LocalServerPort
	int randomServerPort;

	@Test
	void teste_registrar_novojogador() throws URISyntaxException {

		/*
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
		//Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

		// Note: here we are making this converter to process any kind of response, 
		// not only application/*json, which is the default behaviour
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));        
		messageConverters.add(converter);  
		testRestTemplate.setMessageConverters(messageConverters); 
		*/
		
		

		URI uri = new URI("http://localhost:" + randomServerPort + "/api/auth/signup");
		SignupRequest request = new SignupRequest();
		request.setUsername("Jogador 1");
		request.setPassword("123456");
		ResponseEntity<MessageResponse> response = testRestTemplate.postForEntity(uri, request, MessageResponse.class);

		String expectedMessage = "Jogador 1";
		String actualMessage =response.getBody().getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

}
