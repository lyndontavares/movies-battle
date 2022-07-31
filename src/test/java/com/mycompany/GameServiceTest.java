package com.mycompany;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import com.mycompany.game.services.GameServiceImpl;
import com.mycompany.game.services.MovieServiceImpl;
import com.mycompany.models.User;
import com.mycompany.models.enums.GameStatus;
import com.mycompany.payload.response.RankingResponse;
import com.mycompany.repository.RoundRepository;
import com.mycompany.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class GameServiceTest {

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private RoundRepository roundRepository;

	@MockBean
	private MovieServiceImpl movieService;

	@Autowired
	private GameServiceImpl gameService;

	@Test
	void teste_ranking_de_jogadores_com_jagadores() {
		
	
		BDDMockito.when(userRepository.findAll(Sort.by(Sort.Direction.ASC, "score"))).thenReturn(usersFake());
		
		List<RankingResponse> ranking = gameService.ranking();

		assertEquals(ranking.size(), 2);

	}

	private User user1Fake() {
		User u1 = new User();
		u1.setUsername("jogador01");
		u1.setStatus(GameStatus.JOGANDO);
		u1.setScore(10);
		u1.setContaadorErros(0);
		u1.setPassword("123");
		return u1;
	}
	
	private User user2Fake() {
		User u2 = new User();
		u2.setUsername("jogador01");
		u2.setScore(9);
		u2.setStatus(GameStatus.JOGANDO);
		u2.setContaadorErros(0);
		u2.setPassword("123");
		return u2;
	}
	
	private List<User> usersFake() {
			
		List<User> users = new ArrayList<>();
		users.add(user1Fake());
		users.add(user2Fake());
		
		return users;
	}

}
