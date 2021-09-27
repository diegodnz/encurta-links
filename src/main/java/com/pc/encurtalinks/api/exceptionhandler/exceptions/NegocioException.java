package com.pc.encurtalinks.api.exceptionhandler.exceptions;

import org.springframework.http.HttpStatus;

public class NegocioException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	
	public NegocioException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
