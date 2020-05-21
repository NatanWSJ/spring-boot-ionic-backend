package com.natanjesus.cursomc.resources;

import java.util.List;

import com.natanjesus.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.natanjesus.cursomc.domain.Pedido;

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
	
}
