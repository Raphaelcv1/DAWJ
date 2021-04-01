package br.com.rapha.atividade2.atividade2.controle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.rapha.atividade2.atividade2.modelo.Produto;
import br.com.rapha.atividade2.atividade2.modelo.ProdutoRepository;

@Controller
public class ProdutoControle {
	
	private ProdutoRepository produtoRepo;

	public ProdutoControle(ProdutoRepository produtoRepo) {
		this.produtoRepo = produtoRepo;
	}

	@GetMapping("/produtos")
	public String produto(Model model) {
		model.addAttribute("listaProdutos", produtoRepo.findAll());
		return "/produtos/index";
	};

	@GetMapping("/produtos/nova")
	public String novaProduto(@ModelAttribute("produto") Produto produto) {
		return "produtos/form";
	}
	
	@GetMapping("/produtos/{id}")
	public String alterarProduto(@PathVariable("id") long id, Model model) {
		java.util.Optional<Produto> produtoOpt =  produtoRepo.findById(id);
		if(produtoOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessao inválida");
		}
		model.addAttribute("produto", produtoOpt.get());
		return "produtos/form";
	}
	
	@PostMapping("/produtos/salvar")
	public String salvarProduto(@ModelAttribute("produto") Produto produto) {
		produtoRepo.save(produto);
		return "redirect:/produtos";
	}
	
	@GetMapping("/produtos/excluir/{id}")
	public String excluirProduto(@PathVariable("id") long id) {
		java.util.Optional<Produto> produtoOpt =  produtoRepo.findById(id);
		if(produtoOpt.isEmpty()) {
			throw new IllegalArgumentException("Produto inválido");
		}
		produtoRepo.delete(produtoOpt.get());
		return "redirect:/produtos";
	}

}
