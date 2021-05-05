package br.com.fiap.produtos.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.produtos.mvc.dto.Produto;
import br.com.fiap.produtos.mvc.service.impl.ProdutoServiceImpl;

@Controller
public class ProdutoController {
	
	@Autowired
	ProdutoServiceImpl service;

	@GetMapping("novo-produto")
	public ModelAndView homeProduto() {
		ModelAndView view = new ModelAndView("produto");
		view.addObject(new Produto());
		return view;
	}

	@PostMapping("salvar-produto")
	public String salvar(@Valid Produto produto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute(produto);
			return "produto";
		}
		service.salvaProduto(produto);
		return "redirect:/";
	}
	
	@GetMapping("/produto/{id}")
	public ModelAndView editarProduto(@PathVariable Long id) {
		ModelAndView view = new ModelAndView("produto");
		Produto produto = service.editarProduto(id);
		view.addObject("produto", produto);
		return view;
	}
	
	@GetMapping("produto/excluir/{id}")
	public String excluirProduto(@PathVariable Long id) {
		service.excluirProduto(id);
		return "redirect:/";
	}
	
}
