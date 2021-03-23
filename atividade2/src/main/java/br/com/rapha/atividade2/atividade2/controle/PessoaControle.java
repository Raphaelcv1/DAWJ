package br.com.rapha.atividade2.atividade2.controle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sun.el.stream.Optional;

import br.com.rapha.atividade2.atividade2.modelo.Pessoa;
import br.com.rapha.atividade2.atividade2.modelo.PessoaRepository;

@Controller
public class PessoaControle {

	private PessoaRepository pessoaRepo;

	public PessoaControle(PessoaRepository pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}

	@GetMapping("/pessoas")
	public String pessoa(Model model) {
		model.addAttribute("listaPessoas", pessoaRepo.findAll());
		return "/pessoas/index";
	};

	@GetMapping("/pessoas/nova")
	public String novaPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		return "pessoas/form";
	}
	
	@GetMapping("/pessoas/{id}")
	public String alterarPessoa(@PathVariable("id") long id, Model model) {
		java.util.Optional<Pessoa> pessoaOpt =  pessoaRepo.findById(id);
		if(pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessao inválida");
		}
		model.addAttribute("pessoa", pessoaOpt.get());
		return "pessoas/form";
	}
	
	@PostMapping("/pessoas/salvar")
	public String salvarPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		pessoaRepo.save(pessoa);
		return "redirect:/pessoas";
	}
	
	@GetMapping("/pessoas/excluir/{id}")
	public String excluirPessoa(@PathVariable("id") long id) {
		java.util.Optional<Pessoa> pessoaOpt =  pessoaRepo.findById(id);
		if(pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessao inválida");
		}
		pessoaRepo.delete(pessoaOpt.get());
		return "redirect:/pessoas";
	}
	
	
}
