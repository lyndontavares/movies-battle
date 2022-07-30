package com.mycompany.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.mycompany.models.enums.Choice;

@Entity
@Table(name = "round")
public class Round {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch =FetchType.EAGER )
	private User user;

	private String idFilmeA;

	private String idFilmeB;

	@NotNull
	@Column(length = 1)
	@Enumerated(EnumType.STRING)
	private Choice choice;

	
	@NotNull
	@Column(length = 1)
	@Enumerated(EnumType.STRING)
	private Choice choiceAnswer;
	
	@Transient
	private boolean isCorrectAnswer;

	public Choice getChoice() {
		return choice;
	}

	public void setChoice(Choice choice) {
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
 
	public Choice getChoiceAnswer() {
		return choiceAnswer;
	}

	public void setChoiceAnswer(Choice choiceAnswer) {
		this.choiceAnswer = choiceAnswer;
	}

	public boolean isCorrectAnswer() {
		return  choice.equals(choiceAnswer) ;
	}


}
