package com.pc.encurtalinks.api.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.pc.encurtalinks.domain.links.services.RedirecionarLinkService;

@Controller
public class RedirecionarLinkController {
	
	@Autowired
	private RedirecionarLinkService redirecionarLinkService;
	
	@GetMapping("{codigoEncurtado}")
	public ModelAndView redirecionar(HttpServletResponse response, @PathVariable String codigoEncurtado) {
		return redirecionarLinkService.redirecionar(response, codigoEncurtado);	    
	}

}
