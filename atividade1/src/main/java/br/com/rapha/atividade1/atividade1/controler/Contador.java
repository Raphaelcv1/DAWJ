package br.com.rapha.atividade1.atividade1.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Contador {
	public Integer posicao = 0;

	@GetMapping("contador")
	public ModelAndView recebeParam(Model model) {
		ModelAndView mv = new ModelAndView("index");
		posicao++;
		System.out.println(posicao);
		model.addAttribute("posicao", posicao);
		mv.addObject(model);
		return mv;
	}

}
