package com.pc.encurtalinks.domain.links.services;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.pc.encurtalinks.domain.links.Links;
import com.pc.encurtalinks.domain.links.LinksRepository;

@Service
public class RedirecionarLinkService {
	
	@Autowired
	private LinksRepository linksRepository;
	
	/*
	 * Este método recebe um código de encurtamento.
	 * Caso ele seja válido (encontrado no banco de dados), a chamada é redirecionada para a url correspondente
	 * Caso não seja válido, será retornado o html 'notfound.html' 
	 * @param response - Objeto utilizado para redirecionamento
	 * @param codigoEncurtado - O código de encurtamento que poderá corresponder à uma url 
	 */
	public ModelAndView redirecionar(HttpServletResponse response, String codigoEncurtado) {
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		Optional<Links> links = linksRepository.findByLinkEncurtado(codigoEncurtado);
		if (links.isEmpty()) {
			ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("notfound");
		    return modelAndView;			
		} else {			
			response.setHeader("Location", links.get().getLinkOriginal());
		}        
        response.setHeader("Connection", "close");
        return null;
	}
	
}
