package com.pc.encurtalinks.domain.links.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pc.encurtalinks.domain.links.Links;
import com.pc.encurtalinks.domain.links.LinksRepository;
import com.pc.encurtalinks.domain.links.dtos.LinkEncurtadoOutput;
import com.pc.encurtalinks.domain.links.dtos.LinkInput;
import com.pc.encurtalinks.support.Mapper;

@Service
public class EncurtarLinkService {
	
	@Autowired
	private LinksRepository linksRepository;
	
	@Value("${host-url}")
	private String url;
	
	public LinkEncurtadoOutput encurtar(LinkInput linkOriginal) {
		int length = 5;
	    boolean useLetters = true;
	    boolean useNumbers = true;
	    String codigoLink = RandomStringUtils.random(length, useLetters, useNumbers);
	    
	    String linkEncurtado = url + "/" + codigoLink;
	    Links mapeamentoLinks = new Links(linkEncurtado, linkOriginal.getLink());
	    linksRepository.save(mapeamentoLinks);
	    
	    LinkEncurtadoOutput response = new LinkEncurtadoOutput(linkEncurtado);
	    Mapper.copyPropertiesIgnoreNull(mapeamentoLinks, response);
	    return response;
	    
	}
}
