package br.com.rapha.atividade2.atividade2.controle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import br.com.rapha.atividade2.atividade2.modelo.Pedido;
import br.com.rapha.atividade2.atividade2.modelo.PedidoRepository;
import br.com.rapha.atividade2.atividade2.modelo.PessoaRepository;

@Controller
public class PedidoControle {

	private PedidoRepository pedidoRepo;
	private PessoaRepository pessoaRepo;

	public PedidoControle(PedidoRepository pedidoRepo, PessoaRepository pessoaRepo) {
		this.pedidoRepo = pedidoRepo;
		this.pessoaRepo = pessoaRepo;
	}

	@GetMapping("/pedidos")
	public String pedido(Model model) {
		model.addAttribute("listaPedidos", pedidoRepo.findAll());
		model.addAttribute("listaPessoas", pessoaRepo.findAll());
		return "/pedidos/index";
	};

	@GetMapping("/pedidos/nova")
	public String novaPedido(@ModelAttribute("pedido") Pedido pedido, Model model) {
		model.addAttribute("listaPessoas", pessoaRepo.findAll());
		return "pedidos/form";
	}

	@GetMapping("/pedidos/{id}")
	public String alterarPedido(@PathVariable("id") long id, Model model) {
		java.util.Optional<Pedido> pedidoOpt = pedidoRepo.findById(id);
		if (pedidoOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida");
		}
		model.addAttribute("listaPessoas", pessoaRepo.findAll());
		model.addAttribute("pedido", pedidoOpt.get());
		return "pedidos/form";
	}

	@PostMapping("/pedidos/salvar")
	public String salvarPedido(@ModelAttribute("pedido") Pedido pedido) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		Instant dateA = date.toInstant();
		pedido.setData(dateA);
		
		pedidoRepo.save(pedido);
		return "redirect:/pedidos";
	}

	@GetMapping("/pedidos/excluir/{id}")
	public String excluirPedido(@PathVariable("id") long id) {
		java.util.Optional<Pedido> pedidoOpt = pedidoRepo.findById(id);
		if (pedidoOpt.isEmpty()) {
			throw new IllegalArgumentException("Pedido inválido");
		}
		pedidoRepo.delete(pedidoOpt.get());
		return "redirect:/pedidos";
	}

}
