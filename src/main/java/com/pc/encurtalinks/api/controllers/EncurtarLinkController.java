package com.pc.encurtalinks.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pc.encurtalinks.api.exceptionhandler.dtos.Problema;
import com.pc.encurtalinks.domain.links.dtos.LinkEncurtadoOutput;
import com.pc.encurtalinks.domain.links.dtos.LinkInput;
import com.pc.encurtalinks.domain.links.services.EncurtarLinkService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("encurtar-link")
public class EncurtarLinkController {
	             
	@Autowired
	private EncurtarLinkService encurtarLinkService;
	
	// TODO Validar url
	
	// ** Encurtar link **
	@ApiOperation(
			value = "Recebe uma url e retorna um link encurtado para ela"
			)
	@ApiResponses(value = {
			@ApiResponse(
					code = 200,
					message = "Retorna o link encurtado",
					response = LinkEncurtadoOutput.class
					),
			@ApiResponse(
					code = 400,
					message = "Retorna erro caso a url não seja válida",
					response = Problema.class
					)
	})
	@PostMapping
	public ResponseEntity<LinkEncurtadoOutput> encurtar(@RequestBody @Valid LinkInput linkOriginal) {
		return encurtarLinkService.encurtar(linkOriginal);
	}

}
