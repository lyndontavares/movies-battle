package com.mycompany.models;

public class Ranking {
	
	private String nome;

	private Double pontuacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Double pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Ranking(String nome, Double pontuacao) {
		super();
		this.nome = nome;
		this.pontuacao = pontuacao;
	}
	
	
	
}
