package com.mycompany.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mycompany.models.enums.GameStatus;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	private int contaadorErros;
	
	private double score;

	@Enumerated(EnumType.STRING)
	@Column(length = 11)
    @NotNull
	private GameStatus status;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.status= GameStatus.NAO_JOGANDO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public int getContaadorErros() {
		return contaadorErros;
	}

	public void setContaadorErros(int contaadorErros) {
		this.contaadorErros = contaadorErros;
	}
	
	public void incrementarScore() {
		score += 1;
	}
	
	public void incrementarErros() {
		contaadorErros += 1;
	}

}
