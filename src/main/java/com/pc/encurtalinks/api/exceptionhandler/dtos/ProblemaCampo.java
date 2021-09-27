package com.pc.encurtalinks.api.exceptionhandler.dtos;

import io.swagger.annotations.ApiModelProperty;

public class ProblemaCampo {

	@ApiModelProperty(position = 0, example = "link")
	private String nome;
	
	@ApiModelProperty(position = 1, example = "Link inválido")
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