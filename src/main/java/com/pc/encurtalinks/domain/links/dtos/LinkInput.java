package com.pc.encurtalinks.domain.links.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LinkInput {
	
	@NotBlank
	@Size(min = 4, max = 255)
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
