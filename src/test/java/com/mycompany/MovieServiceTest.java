package com.mycompany;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.mycompany.game.services.MovieServiceImpl;

@SpringBootTest
class MovieServiceTest {

	@Autowired
	MovieServiceImpl movieService;

	@Test
	void teste_lista_filmes_preenchida() throws Exception {
		RestTemplate testRestTemplate = new RestTemplate();
		movieService.moviesInit(testRestTemplate);
		assertNotNull(movieService.getMovies());
	}

}
