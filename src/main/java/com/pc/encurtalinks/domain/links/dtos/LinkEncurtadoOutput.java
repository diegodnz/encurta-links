package com.pc.encurtalinks.domain.links.dtos;

import io.swagger.annotations.ApiModelProperty;

public class LinkEncurtadoOutput {

	@ApiModelProperty(example = "zg.com.br/gkMTZ")
	private String link;

	public LinkEncurtadoOutput(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
