package com.mycompany.models.dto;

public class Ranking {

	
	private String nome;

	private double pontuacao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(double pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Ranking(String nome, double pontuacao) {
		super();
		this.nome = nome;
		this.pontuacao = pontuacao;
	}
	
	public Ranking() {
		
	}

}
