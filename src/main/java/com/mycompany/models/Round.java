package com.mycompany.models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.mycompany.models.enums.RoundChoice;

public class Round {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

	private String idFilmeA;

	private String idFilmeB;

	@Enumerated(EnumType.STRING)
	private RoundChoice choice;

	private boolean isMatch;

	public RoundChoice getChoice() {
		return choice;
	}

	public void setChoice(RoundChoice choice) {
		this.choice = choice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIdFilmeA() {
		return idFilmeA;
	}

	public void setIdFilmeA(String idFilmeA) {
		this.idFilmeA = idFilmeA;
	}

	public String getIdFilmeB() {
		return idFilmeB;
	}

	public void setIdFilmeB(String idFilmeB) {
		this.idFilmeB = idFilmeB;
	}

	public boolean isMatch() {
		return idFilmeA!=null && idFilmeB!=null && idFilmeA.equals(idFilmeB) ;
	}


}
