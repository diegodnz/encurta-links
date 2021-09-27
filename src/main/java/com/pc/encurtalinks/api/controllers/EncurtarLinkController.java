package com.pc.encurtalinks.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pc.encurtalinks.domain.links.dtos.LinkEncurtadoOutput;
import com.pc.encurtalinks.domain.links.dtos.LinkInput;
import com.pc.encurtalinks.domain.links.services.EncurtarLinkService;

@RestController
@RequestMapping("encurtar-link")
public class EncurtarLinkController {
	             
	@Autowired
	private EncurtarLinkService encurtarLinkService;
	
	// TODO Documentação swagger
	// TODO Tratar mensagens exceções de validação de dados
	// TODO Validar url
	@PostMapping
	public LinkEncurtadoOutput encurtar(@RequestBody @Valid LinkInput linkOriginal) {
		return encurtarLinkService.encurtar(linkOriginal);
	}

}
