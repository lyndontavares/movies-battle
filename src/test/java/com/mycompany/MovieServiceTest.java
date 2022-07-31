package com.mycompany;

 
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.mycompany.game.services.MovieServiceImpl;
import com.mycompany.models.dto.MovieRound;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class MovieServiceTest {

	@Autowired
	MovieServiceImpl movieService;

	@Test
	@Order(1) 
	void teste_lista_filmes_preenchida() throws Exception {
		RestTemplate testRestTemplate = new RestTemplate();

		movieService.moviesInit(testRestTemplate);

		assertNotNull(movieService.getMovies());
	}

	@Test
	@Order(1)
	void teste_metodo_getmovie() throws Exception {

		String id = movieService.getMovies().get(0).getImdbID();

		assertNotNull(movieService.getMovie(id));
	}

	@Test
	@Order(1)
	void teste_metodo_getmovie_id_nulo() throws Exception {

		assertNull(movieService.getMovie(null));

	}

	@Test
	@Order(1)
	void teste_metodo_getmovieround() {

		MovieRound m = movieService.getMovieRound();
		assertNotNull(m);

	}

	@Test
	@Order(2)
	void teste_metodo_getmovieround_como_movies_tamanho_zero() {

		try {
			movieService.moviesInit(null);
		} catch (Exception e) {
		}

		Exception exception = assertThrows(Exception.class, () -> {
			movieService.getMovieRound();
		});

		String expectedMessage = "Falha carregando Movies";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

}
