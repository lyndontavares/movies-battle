package com.mycompany.game.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mycompany.models.Movie;
import com.mycompany.models.MovieWrap;

@Service
public class MovieService {
	
	private static final Logger log = LoggerFactory.getLogger(MovieService.class);
	
	private String API_MOVIES = "https://www.omdbapi.com/";
	
	// É necessário fazer registro no site para comseguir a APIKEY necessária a todos os requests
	private String API_KEY = "9308bef1"; 
	
	// Mantém a lista de filmes 
	private List<Movie> movies;

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

	public List<Movie> getMovies() {
		return movies;
	};
	
	

}
