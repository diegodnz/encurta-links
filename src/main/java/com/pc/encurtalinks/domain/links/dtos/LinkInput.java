package com.pc.encurtalinks.domain.links.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class LinkInput {
	
	@ApiModelProperty(example = "www.google.com.br")
	@NotBlank
	@Size(min = 4, max = 255)
	@Pattern(regexp = "((https?):\\/\\/)?[^\\s/$.?#]{1,}[.][^\\s]{2,}", message = "URL inválida")
	private String link;
	
	public LinkInput() {}

	public LinkInput(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
