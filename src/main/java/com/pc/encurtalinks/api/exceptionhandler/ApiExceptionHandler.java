package com.pc.encurtalinks.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pc.encurtalinks.api.exceptionhandler.dtos.Problema;
import com.pc.encurtalinks.api.exceptionhandler.dtos.ProblemaCampo;
import com.pc.encurtalinks.api.exceptionhandler.exceptions.CampoException;
import com.pc.encurtalinks.api.exceptionhandler.exceptions.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(NegocioException.class)	
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		HttpStatus status = ex.getStatus();	

		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setDataHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(CampoException.class)	
	public ResponseEntity<Object> handleCampoException(CampoException ex, WebRequest request) {
		HttpStatus status = ex.getStatus();	

		ArrayList<ProblemaCampo> campos = new ArrayList<ProblemaCampo>();
		campos.add(new ProblemaCampo(ex.getNomeCampo(), ex.getMensagem()));
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo("Requisição com campos faltando ou preenchidos incorretamente");
		problema.setDataHora(OffsetDateTime.now());
		
		problema.setCampos(campos);
		problema.setPossuiCampos(true);
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleValidation(ConstraintViolationException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;	
	
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo(ex.getMessage());
		problema.setDataHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		ArrayList<ProblemaCampo> campos = new ArrayList<ProblemaCampo>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError)error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new ProblemaCampo(nome, mensagem));
		}
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo("Requisição com campos faltando ou preenchidos incorretamente");
		problema.setDataHora(OffsetDateTime.now());
		
		problema.setCampos(campos);
		problema.setPossuiCampos(true);
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	
	}
}