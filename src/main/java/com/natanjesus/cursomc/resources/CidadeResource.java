package com.natanjesus.cursomc.resources;

import java.util.List;

import com.natanjesus.cursomc.services.CategoriaService;
import com.natanjesus.cursomc.services.CidadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.natanjesus.cursomc.domain.Categoria;
import com.natanjesus.cursomc.domain.Cidade;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

	@Autowired
	CidadeService cidadeService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cidade>> findAll() {
		return ResponseEntity.ok(this.cidadeService.findAll());
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cidade> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.cidadeService.findById(id));
	}
	
}
