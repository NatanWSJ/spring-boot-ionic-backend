package com.natanjesus.cursomc.resources;

import java.net.URI;
import java.util.List;

import com.natanjesus.cursomc.domain.Cliente;
import com.natanjesus.cursomc.dto.ClienteDTO;
import com.natanjesus.cursomc.dto.ClienteNewDTO;
import com.natanjesus.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.natanjesus.cursomc.domain.Cliente;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	ClienteService clienteService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAll() {
		return ResponseEntity.ok(this.clienteService.findAll());
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.clienteService.findById(id));
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		this.clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<URI> insert(@Valid @RequestBody ClienteNewDTO clienteDTO) {
		Cliente cliente = this.clienteService.fromDTO(clienteDTO);
		cliente = this.clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente cliente) {
		return ResponseEntity.ok(this.clienteService.update(id, cliente));
	}

	@RequestMapping(value = "/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0")Integer page,
			@RequestParam(value="size", defaultValue="24")Integer size,
			@RequestParam(value="direction", defaultValue="ASC")String direction,
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy) {
		return ResponseEntity.ok(this.clienteService.findPage(page, size, direction, orderBy));
	}
	
}
