package br.com.rapha.atividade2.atividade2.controle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.rapha.atividade2.atividade2.modelo.PessoaRepository;

@Controller
public class PessoaControle {
	
	private  PessoaRepository pessoaRepo;
	
	public PessoaControle(PessoaRepository pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}

	@GetMapping("/pessoas")
	public String pessoa(Model model) {
		model.addAttribute("listaPessoas", pessoaRepo.findAll());
		return "/pessoas/index";
	};
}
