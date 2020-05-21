package com.natanjesus.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanjesus.cursomc.domain.Pedido;
import com.natanjesus.cursomc.repository.PedidoRepository;
import com.natanjesus.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	public List<Pedido> findAll() {
		return this.pedidoRepository.findAll();
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = this.pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "
				.concat(id.toString()).concat(", Tipo: ".concat(Pedido.class.getName()))));
	}

	public Pedido save(Pedido pedido) {
		return this.save(pedido);
	}

	public void saveAll(List<Pedido> pedidos) {
		this.pedidoRepository.saveAll(pedidos);
	}

}
