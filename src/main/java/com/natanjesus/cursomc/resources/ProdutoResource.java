package com.natanjesus.cursomc.resources;

import com.natanjesus.cursomc.domain.Produto;
import com.natanjesus.cursomc.dto.CategoriaDTO;
import com.natanjesus.cursomc.dto.ProdutoDTO;
import com.natanjesus.cursomc.resources.utils.URL;
import com.natanjesus.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	ProdutoService produtoService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAll() {
		return ResponseEntity.ok(this.produtoService.findAll());
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.produtoService.findById(id));
	}

	@RequestMapping(value = "/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0")Integer page,
			@RequestParam(value="size", defaultValue="24")Integer size,
			@RequestParam(value="direction", defaultValue="ASC")String direction,
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy,
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		return ResponseEntity.ok(this.produtoService.search(nomeDecoded, ids, page, size, direction, orderBy));
	}
	
}
