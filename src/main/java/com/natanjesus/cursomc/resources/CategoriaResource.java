package com.natanjesus.cursomc.resources;

import java.net.URI;
import java.util.List;

import com.natanjesus.cursomc.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.natanjesus.cursomc.domain.Categoria;
import com.natanjesus.cursomc.services.CategoriaService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	CategoriaService categoriaService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		return ResponseEntity.ok(this.categoriaService.findAll());
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.categoriaService.findById(id));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<URI> insert(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria categoria = this.categoriaService.fromDTO(categoriaDTO);
		categoria = this.categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody Categoria categoria) {
		return ResponseEntity.ok(this.categoriaService.update(id, categoria));
	}
	
	@RequestMapping(value = "/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0")Integer page,
			@RequestParam(value="size", defaultValue="24")Integer size,
			@RequestParam(value="direction", defaultValue="ASC")String direction,
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy) {
		return ResponseEntity.ok(this.categoriaService.findPage(page, size, direction, orderBy));
	}
	
}
