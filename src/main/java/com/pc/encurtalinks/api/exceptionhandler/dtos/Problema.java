package com.pc.encurtalinks.api.exceptionhandler.dtos;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)
public class Problema {

	@ApiModelProperty(position = 0, example = "400")
	private Integer status;
	
	@ApiModelProperty(position = 1, example = "2021-09-26T22:41:32.3516468-03:00")
	private OffsetDateTime dataHora;
	
	@ApiModelProperty(position = 2, example = "Requisição com campos faltando ou preenchidos incorretamente")
	private String titulo;
	
	@ApiModelProperty(position = 3, example = "true")
	private boolean possuiCampos;
	
	@ApiModelProperty(position = 4)
	private List<ProblemaCampo> campos;

	public Problema() {
		this.possuiCampos = false;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public OffsetDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isPossuiCampos() {
		return possuiCampos;
	}

	public void setPossuiCampos(boolean possuiCampos) {
		this.possuiCampos = possuiCampos;
	}

	public List<ProblemaCampo> getCampos() {
		return campos;
	}

	public void setCampos(List<ProblemaCampo> campos) {
		this.campos = campos;
	}

}
