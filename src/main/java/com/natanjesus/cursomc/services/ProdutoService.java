package com.natanjesus.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.natanjesus.cursomc.domain.Categoria;
import com.natanjesus.cursomc.dto.ProdutoDTO;
import com.natanjesus.cursomc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.natanjesus.cursomc.domain.Produto;
import com.natanjesus.cursomc.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
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

	public Page<ProdutoDTO> search(String nome, List<Integer> listCategorias, Integer page, Integer size, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = this.categoriaRepository.findAllById(listCategorias);
		Page<Produto> produtos = this.produtoRepository.search(nome, categorias, pageRequest);
		return produtos.map(produto -> new ProdutoDTO(produto));
	}

}
