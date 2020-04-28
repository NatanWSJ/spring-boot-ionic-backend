package com.natanjesus.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanjesus.cursomc.domain.Categoria;
import com.natanjesus.cursomc.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(id);
		return categoria.orElse(null);
	}
	
	public Categoria save(Categoria categoria) {
		return this.save(categoria);
	}
	
	public void saveAll(List<Categoria> categorias) {
		this.categoriaRepository.saveAll(categorias);
	}

}
