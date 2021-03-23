package br.com.rapha.atividade2.atividade2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.rapha.atividade2.atividade2.modelo.Pessoa;
import br.com.rapha.atividade2.atividade2.modelo.PessoaRepository;

@Component
public class PopulacaoInicialBanco implements CommandLineRunner {

	@Autowired
	private PessoaRepository pessoaRepo;

	@Override
	public void run(String... args) throws Exception {

		Pessoa p1 = new Pessoa("Juca");
		p1.setEmail("juca@a.com");
		p1.setTelefone("44.1234.1234");
		Pessoa p2 = new Pessoa("Zéca");
		p2.setEmail("zeca@a.com");
		p2.setTelefone("44.4321.4321");

		pessoaRepo.save(p1);
		pessoaRepo.save(p2);
	}

}
