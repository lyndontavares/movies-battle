package com.mycompany.payload.response;

public class RankingResponse {
	
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

	public RankingResponse(String nome, Double pontuacao) {
		super();
		this.nome = nome;
		this.pontuacao = pontuacao;
	}
	
	
	
}
