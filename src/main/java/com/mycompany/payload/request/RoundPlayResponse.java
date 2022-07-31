package com.mycompany.payload.request;

import com.mycompany.models.dto.Movie;
import com.mycompany.models.enums.Choice;

public class RoundPlayResponse {

	private boolean acertou;

	private double pontuacao;

	private int erros;

	private Choice opcaoCorreta;

	private Movie movie;

	private boolean jogoFinalizado;

	public boolean isJogoFinalizado() {
		return jogoFinalizado;
	}

	public void setJogoFinalizado(boolean jogoFinalizado) {
		this.jogoFinalizado = jogoFinalizado;
	}

	public boolean isAcertou() {
		return acertou;
	}

	public void setAcertou(boolean acertou) {
		this.acertou = acertou;
	}

	public double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(double pontuacao) {
		this.pontuacao = pontuacao;
	}

	public int getErros() {
		return erros;
	}

	public void setErros(int erros) {
		this.erros = erros;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Choice getOpcaoCorreta() {
		return opcaoCorreta;
	}

	public void setOpcaoCorreta(Choice opcaoCorreta) {
		this.opcaoCorreta = opcaoCorreta;
	}

}
