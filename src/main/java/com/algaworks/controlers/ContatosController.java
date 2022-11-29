package com.algaworks.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContatosController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
}
