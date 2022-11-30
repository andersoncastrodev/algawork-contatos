package com.algaworks.controlers;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		
		ModelAndView modelAndView = new ModelAndView("lista");
		
		modelAndView.addObject("contatos", LISTA_CONTATOS);
		
		return modelAndView;
	}
	
	@GetMapping("/contatos/novo")
	public ModelAndView novo() {
		
		ModelAndView modelAndView = new ModelAndView("formulario");
		
		modelAndView.addObject("contato", new Contato() );
		
		return modelAndView;
	}
	
	@PostMapping("/contatos")
	public String cadastrar(Contato contato) {		
		//Cria um codigo randomico 
		String id = UUID.randomUUID().toString();
		
		//Seta o valor
		contato.setId(id);
		
		LISTA_CONTATOS.add(contato);
	
		return "redirect:/contatos";
	}
	
	@GetMapping("/contatos/{id}/editar")
	public ModelAndView editar(@PathVariable String id) {
			
		ModelAndView modelAndView = new ModelAndView("formulario");
		
		Contato contato = procuraContato(id);
		
		modelAndView.addObject("contato", contato );
		
		return modelAndView;
	}
	
	@PostMapping("/contatos/{id}")
	public String atualizar(Contato contato) {
		
		Contato contatoVelho = procuraContato(contato.getId());
		
		LISTA_CONTATOS.remove(contatoVelho);
		
		LISTA_CONTATOS.add(contato);
		
		return "redirect:/contatos";
	}
	
	@DeleteMapping("/contatos/{id}")
	public String deletar(@PathVariable String id) {
		
		Contato contato = procuraContato(id);
		
		LISTA_CONTATOS.remove(contato);
		
		return "redirect:/contatos";
	}
	
	
	// --------------------------- Metodos Auxiliares ---------------------------- //
	private Contato procuraContato(String id) {
		
		for (int i = 0; i < LISTA_CONTATOS.size(); i++) {
			
			Contato contato = LISTA_CONTATOS.get(i);
			
			if(contato.getId().equals(id)) {
				
				return contato;
			}
			
		}
		return null;
	}
	
	
	
	
}
