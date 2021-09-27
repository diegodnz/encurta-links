package com.pc.encurtalinks.domain.links.dtos;

import io.swagger.annotations.ApiModelProperty;

public class LinkOriginalOutput extends LinkOutput{
	
	@ApiModelProperty(example = "www.google.com.br")
	private String link;
	
	public LinkOriginalOutput(String link) {
		super(link);
	}

}
