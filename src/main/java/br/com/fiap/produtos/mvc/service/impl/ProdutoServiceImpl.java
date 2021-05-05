package br.com.fiap.produtos.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.produtos.mvc.dto.Produto;
import br.com.fiap.produtos.mvc.entity.ProdutoEntity;
import br.com.fiap.produtos.mvc.repository.ProdutoRepository;
import br.com.fiap.produtos.mvc.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	ProdutoRepository repository;

	public List<Produto> listaProdutos() {
		List<ProdutoEntity> lista = repository.findAll();
		List<Produto> produtos = fromTo(lista);
		return produtos;
	}

	public Produto salvaProduto(Produto produto) {
		ProdutoEntity entity = fromTo(produto);
		entity = repository.save(entity);
		Produto produtoRetorno = fromTo(entity);
		return produtoRetorno;
	}

	@Override
	public Produto editarProduto(Long id) {
        ProdutoEntity entity =  repository.findById(id).get();
		return fromTo(entity);
	}

	@Override
	public void excluirProduto(Long id) {
		repository.deleteById(id);
	}

	private List<Produto> fromTo(List<ProdutoEntity> lista) {
		List<Produto> listaDto = new ArrayList<>();
		for (ProdutoEntity produtoEntity : lista) {
			Produto dto = new Produto();
			dto.setId(produtoEntity.getId());
			dto.setNome(produtoEntity.getNome());
			dto.setSku(produtoEntity.getSku());
			dto.setDescricao(produtoEntity.getDescricao());
			dto.setPreco(produtoEntity.getPreco());
			listaDto.add(dto);
		}
		return listaDto;
	}

	private ProdutoEntity fromTo(Produto produto) {
		ProdutoEntity entity = new ProdutoEntity();
		entity.setId(produto.getId());
		entity.setNome(produto.getNome());
		entity.setPreco(produto.getPreco());
		entity.setDescricao(produto.getDescricao());
		entity.setSku(produto.getSku());

		return entity;
	}

	private Produto fromTo(ProdutoEntity entity) {
		Produto produto = new Produto();
		produto.setId(entity.getId());
		produto.setNome(entity.getNome());
		produto.setPreco(entity.getPreco());
		produto.setDescricao(entity.getDescricao());
		produto.setSku(entity.getSku());

		return produto;
	}
}
