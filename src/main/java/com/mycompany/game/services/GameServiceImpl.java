package com.mycompany.game.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycompany.models.User;
import com.mycompany.models.enums.GameStatus;
import com.mycompany.payload.response.RankingResponse;
import com.mycompany.models.enums.Choice;
import com.mycompany.repository.UserRepository;
import com.mycompany.security.jwt.JwtUtils;

@Service
public class GameServiceImpl {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	UserRepository userRepository;

	public User iniciarGame(HttpServletRequest request) {
		User user = getUser(request);
		user.setScore(Double.valueOf(0));
		user.setStatus(GameStatus.JOGANDO);
		userRepository.save(user);
		return user;
	}

	public User finalizarGame(HttpServletRequest request) {
		User user = getUser(request);
		user.setStatus(GameStatus.FINALIZADO);
		userRepository.save(user);
		return user;
	}

	public User playRound(HttpServletRequest request, Choice choice) {
		User user = getUser(request);
		
		return user;
	}

	public List<RankingResponse> ranking() {

		List<RankingResponse> lista = new ArrayList<>();

		userRepository.findAll(Sort.by(Sort.Direction.ASC, "score")).forEach(u -> {
			lista.add(new RankingResponse(u.getUsername(), u.getScore()));
		});
		return lista;
	}

	private User getUser(HttpServletRequest request) {
		String username = jwtUtils.getUserNameFromJwtToken(jwtUtils.getJwtFromCookies(request));
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado para o nome: " + username));
		return user;
	}

}
