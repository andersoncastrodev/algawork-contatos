package com.algaworks.controlers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.model.Contato;

@Controller
public class ContatosController {

	private static final ArrayList<Contato> LISTA_CONTATOS = new ArrayList<>();
	
	static {
		
		LISTA_CONTATOS.add( new Contato("1", "Francisco", "85 98585-6585"));
		LISTA_CONTATOS.add( new Contato("2", "Joao", "85 8574-5874"));
		LISTA_CONTATOS.add( new Contato("3", "Fernando", "85 8525-5263"));
		LISTA_CONTATOS.add( new Contato("4", "Maria", "85 99632-5241"));
		LISTA_CONTATOS.add( new Contato("5", "Laura", "85 98574-9632"));
	}
	
	@GetMapping("/")
	public String paginaInicial() {
		return "index";
	}
	
	@GetMapping("/contatos")
	public ModelAndView Listar() {
		
		ModelAndView andView = new ModelAndView("lista");
		
		andView.addObject("contatos", LISTA_CONTATOS);
		
		return andView;
	}
	
	
	
	
}
