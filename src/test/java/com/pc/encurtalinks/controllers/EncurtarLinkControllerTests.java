package com.pc.encurtalinks.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.pc.encurtalinks.domain.links.dtos.LinkInput;
import com.pc.encurtalinks.domain.links.services.EncurtarLinkService;

@SpringBootTest
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
public class EncurtarLinkControllerTests {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private EncurtarLinkService encurtarLinkService;
	
	/*
	 * Método apenas para popular o banco de dados
	 */
	@Test
	public void Adicionar() {
		for (int i = 0; i < 1000000; i++) {
			encurtarLinkService.encurtar(new LinkInput("google.com"));
		}
		assertTrue(true);
	}
	
	/*
	 * Testa o fluxo completo ao encurtar um link.
	 * 1 - Encurta o link original
	 * 2 - Salva o código gerado
	 * 3 - Faz a request para obter o link original a partir do código de encurtamento que foi gerado
	 * 4 - Assegura que o link retornado é igual ao link original
	 */
	@Test
	public void encurtarLink() throws Exception {
		String linkOriginal = "www.google.com.br";
		ResultActions requestEncurtar = mvc.perform(post("/links/encurtar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(String.format("{\"link\": \"%s\"}", linkOriginal)));

		requestEncurtar.andDo(print())
		.andExpect(status().is2xxSuccessful());
		
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		String responseEncurtar = requestEncurtar.andReturn().getResponse().getContentAsString();
		String codigoLink = jsonParser.parseMap(responseEncurtar).get("link").toString();
		
		ResultActions requestObter = mvc.perform(get("/links/obter")
				.queryParam("link", codigoLink));

		requestObter.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andExpect(jsonPath("$.link").value(linkOriginal));

	}
}
