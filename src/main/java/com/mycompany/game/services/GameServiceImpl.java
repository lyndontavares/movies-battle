package com.mycompany.game.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycompany.models.GameStatus;
import com.mycompany.models.Ranking;
import com.mycompany.models.User;
import com.mycompany.repository.UserRepository;
import com.mycompany.security.jwt.JwtUtils;

@Service
public class GameServiceImpl {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	UserRepository userRepository;

	public User getUser(HttpServletRequest request) {
		String username = jwtUtils.getUserNameFromJwtToken(jwtUtils.getJwtFromCookies(request));
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado para o nome: " + username));
		return user;
	}

	public void finilarGame(User user) {
		user.setStatus(GameStatus.FINALIZADO);
		userRepository.save(user);
	}
	
	public void iniciarGame(User user) {
		user.setStatus(GameStatus.JOGANDO);
		userRepository.save(user);
	}
	
	public List<Ranking> ranking() {
		
		List<Ranking> lista = new ArrayList<>();
		
		userRepository.findAll(Sort.by(Sort.Direction.ASC, "score")).forEach(u->{
			lista.add(new Ranking( u.getUsername(), u.getScore()));
		});
		return lista;
	}
}
