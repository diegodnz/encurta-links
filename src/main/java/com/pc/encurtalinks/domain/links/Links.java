package com.pc.encurtalinks.domain.links;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Links {
	
	@Id
	@NotNull
	@Size(max = 32)
	private String linkEncurtado;
	
	@NotNull
	@Size(max = 255)
	private String linkOriginal;
	
	public Links() {}

	public Links(String linkEncurtado, String linkOriginal) {
		this.linkEncurtado = linkEncurtado;
		this.linkOriginal = linkOriginal;
	}

	public String getLinkEncurtado() {
		return linkEncurtado;
	}

	public void setLinkEncurtado(String linkEncurtado) {
		this.linkEncurtado = linkEncurtado;
	}

	public String getLinkOriginal() {
		return linkOriginal;
	}

	public void setLinkOriginal(String linkOriginal) {
		this.linkOriginal = linkOriginal;
	}
	
	

}
