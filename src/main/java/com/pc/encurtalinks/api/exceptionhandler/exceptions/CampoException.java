package com.pc.encurtalinks.api.exceptionhandler.exceptions;

import org.springframework.http.HttpStatus;

public class CampoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	
	private String nomeCampo;
	
	private String mensagem;
	
	public CampoException(HttpStatus status, String nomeCampo, String mensagem) {
		super();
		this.status = status;
		this.nomeCampo = nomeCampo;
		this.mensagem = mensagem;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
