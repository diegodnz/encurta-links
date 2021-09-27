package com.pc.encurtalinks.domain.links.dtos;

import io.swagger.annotations.ApiModelProperty;

public class LinkEncurtadoOutput extends LinkOutput{
	
	@ApiModelProperty(example = "gkMTZ")
	private String link;
	
	public LinkEncurtadoOutput(String link) {
		super(link);				
	}

}
