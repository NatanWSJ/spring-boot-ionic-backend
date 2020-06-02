package com.natanjesus.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanjesus.cursomc.domain.Categoria;
import com.natanjesus.cursomc.repository.CategoriaRepository;
import com.natanjesus.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {
		return this.categoriaRepository.findAll();
	}

	public Categoria findById(Integer id) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: ".concat(id.toString())
				.concat(", Tipo: ".concat(Categoria.class.getName())))
				);
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return this.categoriaRepository.save(categoria);
	}
	
	public void saveAll(List<Categoria> categorias) {
		this.categoriaRepository.saveAll(categorias);
	}

}
