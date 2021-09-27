package com.pc.encurtalinks.domain.links.services;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	/*
     * Este método gera uma string com caracteres aleatórios, incluindo letras e números, com o tamanho informado
     * 
     * @param tamanho - Indica o tamanho da string que será gerada
     * 
     * @return Retorna a string aleatória
     * 
     * @throws IllegalArgumentException será lançada caso o tamanho seja menor que 0
     */
	private String gerarCodigoAleatorio(int tamanho) {
		boolean usarLetras = true;
	    boolean usarNumeros = true;
	    return RandomStringUtils.random(tamanho, usarLetras, usarNumeros);
	}
	
	/*
	 * Encurta o link recebido como parâmetro, gerando um código aleatório de tamanho 5 e salvando o mapeamento no banco de dados
	 * 
	 * @param linkOriginalInput - Objeto que contém o link original que será encurtado
	 * 
	 * @return Retorna um objeto com o código de encurtamento gerado
	 */
	public ResponseEntity<LinkOutput> encurtar(LinkInput linkOriginalInput) {
		String linkOriginal = linkOriginalInput.getLink();		
		
		String codigoLink = gerarCodigoAleatorio(5);
	    while (linksRepository.findById(codigoLink).isPresent()) {
	    	codigoLink = gerarCodigoAleatorio(5);
	    }
	    
	    Links mapeamentoLinks = new Links(codigoLink, linkOriginal);
	    linksRepository.saveNew(codigoLink, linkOriginal);
	    
	    LinkEncurtadoOutput response = new LinkEncurtadoOutput(codigoLink);
	    Mapper.copyPropertiesIgnoreNull(mapeamentoLinks, response);
	    return new ResponseEntity<LinkOutput>(response, HttpStatus.OK);
	    
	}
	
	/*
	 * Obter o link original a partir de um código de encurtamento
	 * 
	 * @param linkEncurtado - Código de encurtamento de uma possível url
	 * 
	 * @return Retorna um objeto com o link original correspondente ao código de encurtamento recebido
	 * 
	 * @throws CampoException será lançada caso o link seja inválido
	 */
	
	public ResponseEntity<LinkOutput> obterLinkOriginal(String linkEncurtado) {
		Optional<Links> links = linksRepository.findById(linkEncurtado);
		if (links.isEmpty()) {
			throw new CampoException(HttpStatus.BAD_REQUEST, "link", "Link inválido");
		} else {
			LinkOriginalOutput response = new LinkOriginalOutput(links.get().getLinkOriginal());
			return new ResponseEntity<LinkOutput>(response, HttpStatus.OK);
		}
	}
}
