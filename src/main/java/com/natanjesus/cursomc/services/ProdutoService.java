package com.natanjesus.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanjesus.cursomc.domain.Produto;
import com.natanjesus.cursomc.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto findById(Integer id) {
		Optional<Produto> produto = this.produtoRepository.findById(id);
		return produto.orElse(null);
	}
	
	public List<Produto> findAll(){
		return this.produtoRepository.findAll();
	}
	
	public Produto save(Produto produto) {
		return this.produtoRepository.save(produto);
	}
	
	public void saveAll(List<Produto> produtos) {
		this.produtoRepository.saveAll(produtos);
	}

}
