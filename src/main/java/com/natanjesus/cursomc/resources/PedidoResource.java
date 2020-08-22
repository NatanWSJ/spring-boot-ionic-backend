package com.natanjesus.cursomc.resources;

import java.net.URI;
import java.util.List;

import com.natanjesus.cursomc.domain.Categoria;
import com.natanjesus.cursomc.dto.CategoriaDTO;
import com.natanjesus.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.natanjesus.cursomc.domain.Pedido;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	PedidoService pedidoService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Pedido>> findAll() {
		return ResponseEntity.ok(this.pedidoService.findAll());
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.pedidoService.findById(id));
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<URI> insert(@Valid @RequestBody Pedido pedido) {
		pedido = this.pedidoService.insert(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
