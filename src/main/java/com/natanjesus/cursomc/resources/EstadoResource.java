package com.natanjesus.cursomc.resources;

import java.util.List;

import com.natanjesus.cursomc.services.CategoriaService;
import com.natanjesus.cursomc.services.EstadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.natanjesus.cursomc.domain.Categoria;
import com.natanjesus.cursomc.domain.Estado;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	EstadoService estadoService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Estado>> findAll() {
		return ResponseEntity.ok(this.estadoService.findAll());
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Estado> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.estadoService.findById(id));
	}
	
}
