package com.pc.encurtalinks.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pc.encurtalinks.api.exceptionhandler.dtos.Problema;
import com.pc.encurtalinks.domain.links.dtos.LinkEncurtadoOutput;
import com.pc.encurtalinks.domain.links.dtos.LinkInput;
import com.pc.encurtalinks.domain.links.dtos.LinkOriginalOutput;
import com.pc.encurtalinks.domain.links.dtos.LinkOutput;
import com.pc.encurtalinks.domain.links.services.EncurtarLinkService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("links")
public class EncurtarLinkController {
	             
	@Autowired
	private EncurtarLinkService encurtarLinkService;
	
	// ** Encurtar link **
	@ApiOperation(
			value = "Recebe uma url e retorna um código de link encurtado para ela"
			)
	@ApiResponses(value = {
			@ApiResponse(
					code = 200,
					message = "Retorna o código para o link encurtado",
					response = LinkEncurtadoOutput.class
					),
			@ApiResponse(
					code = 400,
					message = "Retorna erro caso a url não seja válida",
					response = Problema.class
					)
	})
	@PostMapping("encurtar")
	public ResponseEntity<LinkOutput> encurtar(@RequestBody @Valid LinkInput linkOriginal) {
		return encurtarLinkService.encurtar(linkOriginal);
	}
	
	// ** Obter link original **
	@ApiOperation(
			value = "Recebe o código do link encurtado e retorna o link original"
			)
	@ApiResponses(value = {
			@ApiResponse(
					code = 200,
					message = "Retorna o link original",
					response = LinkOriginalOutput.class
					),
			@ApiResponse(
					code = 400,
					message = "Retorna erro caso o link não seja encontrado",
					response = Problema.class					
					)
	})
	@GetMapping("obter")
	public ResponseEntity<LinkOutput> obterLink(@RequestParam(required = true, name = "link") @ApiParam(required = true, value = "Um código de link encurtado. Ex: gkMTZ") String link) {
		return encurtarLinkService.obterLinkOriginal(link);
	}

}
