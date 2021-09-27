package com.pc.encurtalinks.api.exceptionhandler.dtos;

public class ProblemaCampo {

	private String nome;
	private String mensagem;

	public ProblemaCampo(String nome, String mensagem) {
		super();
		this.nome = nome;
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}