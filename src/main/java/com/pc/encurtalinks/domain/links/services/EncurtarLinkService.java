package com.pc.encurtalinks.domain.links.services;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pc.encurtalinks.api.exceptionhandler.exceptions.CampoException;
import com.pc.encurtalinks.domain.links.Links;
import com.pc.encurtalinks.domain.links.LinksRepository;
import com.pc.encurtalinks.domain.links.dtos.LinkEncurtadoOutput;
import com.pc.encurtalinks.domain.links.dtos.LinkInput;
import com.pc.encurtalinks.domain.links.dtos.LinkOriginalOutput;
import com.pc.encurtalinks.domain.links.dtos.LinkOutput;
import com.pc.encurtalinks.support.Mapper;

@Service
public class EncurtarLinkService {
	
	@Autowired
	private LinksRepository linksRepository;
	
	@Value("${host-url}")
	private String url;
	
	public ResponseEntity<LinkOutput> encurtar(LinkInput linkOriginalInput) {
		String linkOriginal = linkOriginalInput.getLink();		
		
		int length = 5;
	    boolean useLetters = true;
	    boolean useNumbers = true;
	    String codigoLink = RandomStringUtils.random(length, useLetters, useNumbers);
	    
	    Links mapeamentoLinks = new Links(codigoLink, linkOriginal);
	    linksRepository.save(mapeamentoLinks);
	    
	    LinkEncurtadoOutput response = new LinkEncurtadoOutput(codigoLink);
	    Mapper.copyPropertiesIgnoreNull(mapeamentoLinks, response);
	    return new ResponseEntity<LinkOutput>(response, HttpStatus.OK);
	    
	}
	
	public ResponseEntity<LinkOutput> obterLinkOriginal(String linkEncurtado) {
		Optional<Links> links = linksRepository.findById(linkEncurtado);
		if (links.isEmpty()) {
			throw new CampoException(HttpStatus.BAD_REQUEST, "link", "Link inválido");
		} else {
			LinkOriginalOutput response = new LinkOriginalOutput(links.get().getLinkOriginal());
			return new ResponseEntity<LinkOutput>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
