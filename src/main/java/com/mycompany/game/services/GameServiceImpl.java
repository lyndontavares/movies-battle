package com.mycompany.game.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mycompany.models.Round;
import com.mycompany.models.User;
import com.mycompany.models.dto.MovieRound;
import com.mycompany.models.enums.Choice;
import com.mycompany.models.enums.GameStatus;
import com.mycompany.payload.request.RoundReadResponse;
import com.mycompany.payload.response.RankingResponse;
import com.mycompany.repository.RoundRepository;
import com.mycompany.repository.UserRepository;
import com.mycompany.security.jwt.JwtUtils;

@Service
public class GameServiceImpl {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoundRepository roundRepository;

	@Autowired
	MovieServiceImpl movieService;

	/**
	 * iniciarGame
	 * 
	 * @Regra#01 Ao iniciar é GameStatus: JOGANDO
	 * @Regra#02 É zerado jogadas na tabela de round
	 * 
	 * @param request
	 * @return
	 */
	@Transactional
	public User iniciarGame(HttpServletRequest request) {
		User user = getUser(request);
		user.setScore(Double.valueOf(0));
		user.setStatus(GameStatus.JOGANDO);
		userRepository.save(user);
		roundRepository.deleteAllByUser(user);
		return user;
	}

	/**
	 * finalizarGame
	 * 
	 * @Regra#01 Ao finalizar GameStatus: NAO_JOGANDO
	 * 
	 * @param request
	 * @return
	 */

	@Transactional
	public User finalizarGame(HttpServletRequest request) {
		User user = getUser(request);
		user.setStatus(GameStatus.NAO_JOGANDO);
		userRepository.save(user);
		return user;
	}

	/**
	 * playRound
	 * 
	 * @Regra#01 Verificar acerto
	 * @Regra#02 Atualiza potuação do jogador
	 * 
	 * @param request
	 * @param choice
	 * @return
	 */
	public User playRound(HttpServletRequest request, Choice choice) {
		User user = getUser(request);

		return user;
	}

	/**
	 * readRound
	 * 
	 * @Regra#01 Verifica se o jogador está jogando
	 * @Regra#01 O jogo finaliza após 3 erros
	 * 
	 * @param request
	 * @return
	 */
	public RoundReadResponse readRound(HttpServletRequest request) {
		
		User user = getUser(request);
		 
		checkJogadorComStatusJogando(user);
		checkFinalGame(user);
		
		MovieRound movieRound = gerarMovieRoundByUser(user);
		
		Round newRound = new Round();
		newRound.setUser(user);
		newRound.setIdFilmeA(movieRound.getMovieA().getImdbID());
		newRound.setIdFilmeB(movieRound.getMovieB().getImdbID());
		roundRepository.save(newRound);
		
		RoundReadResponse response= new RoundReadResponse();
		response.setRoundID(newRound.getId());
		response.setMovieRound(movieRound);

		return response;
	}

	/**
	 * ranking
	 * 
	 * @description Retorma rankings dos jogadores
	 * @return
	 */
	public List<RankingResponse> ranking() {

		List<RankingResponse> lista = new ArrayList<>();

		userRepository.findAll(Sort.by(Sort.Direction.ASC, "score")).forEach(u -> {
			lista.add(new RankingResponse(u.getUsername(), u.getScore()));
		});
		return lista;
	}

	/**
	 * getUser
	 * 
	 * @description Método auxiliar para pegar jogador logado a partir do request
	 * @param request
	 * @return
	 */
	private User getUser(HttpServletRequest request) {
		String username = jwtUtils.getUserNameFromJwtToken(jwtUtils.getJwtFromCookies(request));
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado para o nome: " + username));
		return user;
	}

	/**
	 * MovieRound
	 * 
	 * @descripion Método que gera um round e evita repetições de movies do game por jogador
	 * 
	 * @importante Foi adicionado um controle de profundidade arbitrário por ser a lista de filmes finita. 
	 * @importante Pode chegar o momento que não haverá pares de filmes distintos.
	 * 
	 * @param user
	 * @return
	 */
	private MovieRound gerarMovieRoundByUser(User user) {
		
		boolean duplicado = false;
		List<Round> rounds = roundRepository.findByUser(user);
		MovieRound newRound = movieService.getMovieRound();
		String a = newRound.getMovieA().getImdbID();
		String b = newRound.getMovieB().getImdbID();
		
        int limiteTentativasParaFormarRound = 1000;  
        
		for (Round r : rounds) {
			String c = r.getIdFilmeA();
			String d = r.getIdFilmeA();
			duplicado = (a.equals(c) || a.equals(d)) && (b.equals(c) || b.equals(d));
			if (duplicado) {
				limiteTentativasParaFormarRound--;
				if ( limiteTentativasParaFormarRound == 0 ) {
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha interna na geração de Rounds!");
				}
				newRound = movieService.getMovieRound();
				a = newRound.getMovieA().getImdbID();
				b = newRound.getMovieB().getImdbID();
			}
		}
		return newRound;
	}

	private void checkJogadorComStatusJogando(User user) {
		if (GameStatus.NAO_JOGANDO.equals(user.getStatus())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jogador deve está com status JOGANDO");
		}
	}

	private void checkFinalGame(User user) {
		if (user.getContaadorErros() == 3) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Final de Jogo! Escolha iniciar Game para jogar novamente");
		}
	}

}
