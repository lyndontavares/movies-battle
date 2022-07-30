package com.mycompany.game.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.mycompany.models.dto.Movie;
import com.mycompany.models.dto.MovieRound;
import com.mycompany.models.dto.MovieWrap;

@Service
public class MovieServiceImpl {
	
	private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);
	
	private String API_MOVIES = "https://www.omdbapi.com/";
	
	// É necessário fazer registro no site para comseguir a APIKEY necessária a todos os requests
	private String API_KEY = "9308bef1"; 
	
	// Mantém a lista de filmes 
	private List<Movie> movies;

	/**
	 * moviesInit
	 * 
	 * @description Inicia banco de filmes pegando da api: https://www.omdbapi.com/
	 * @Regra#01 Ao iniciar aplicação é preparado a lista de filmes que será utilizada no Game
	 * @param restTemplate
	 * @throws Exception
	 */
	public void moviesInit(RestTemplate restTemplate) throws Exception {
		
		//1. Inicializa lista de filmes
		movies = new ArrayList<>();
		
		//2. Pega lista de filmes da API
		MovieWrap lista = restTemplate.getForObject(API_MOVIES + "?s=Life&i=&apikey=9308bef1", MovieWrap.class);

		log.info(lista.toString());

		//3. Pega info de cada filme e adicinar a lista de filmes 
		lista.getSearch().forEach(m -> {
			Movie movie = restTemplate
					.getForObject(String.format(API_MOVIES + "?i=%s&apikey=" + API_KEY, m.getImdbID()), Movie.class);
			movies.add(movie);
			log.info(movie.toString());
		});
	}
	
	/**
	 * getMovies
	 * 
	 * @description Retorna lista de filmes
	 * @return
	 */

	public List<Movie> getMovies() {
		return movies;
	};
	

	/**
	 * getMovieRound
	 * 
	 * @description returna dupla de filmes para rodada
	 * @Regra#01 Pega aleatoriamente
	 * @Regra#02 Não pode repetir
	 * @return
	 */
	public MovieRound getMovieRound() {
		
		if (movies.size() == 0 ) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha carregando Movies!");
		}
		
		MovieRound round = new MovieRound();
		
		Random rand = new Random();
		int movieA = 0;
		int movieB = 0;
		int totalMovies = movies.size();

		while ( movieA == movieB ) {
			movieA = rand.nextInt(totalMovies);
			movieB = rand.nextInt(totalMovies);
		}

		round.setMovieA( movies.get(movieA) );
		round.setMovieB( movies.get(movieB) );
		
		return round;
		
	}
	

}
