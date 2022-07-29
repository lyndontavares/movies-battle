package com.mycompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.mycompany.game.services.MovieService;

@SpringBootApplication
public class SpringBootSecurityLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityLoginApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Autowired
	private MovieService movieService;
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		movieService.moviesInit(restTemplate);
		return null;
	}

}
