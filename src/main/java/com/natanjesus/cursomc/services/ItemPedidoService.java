package com.natanjesus.cursomc.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanjesus.cursomc.domain.ItemPedido;
import com.natanjesus.cursomc.repository.ItemPedidoRepository;
import com.natanjesus.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ItemPedidoService {
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public List<ItemPedido> findAll() {
		return this.itemPedidoRepository.findAll();
	}

	public ItemPedido findById(Integer id) {
		Optional<ItemPedido> itemPedido = this.itemPedidoRepository.findById(id);
		return itemPedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: ".concat(id.toString())
				.concat(", Tipo: ".concat(ItemPedido.class.getName())))
				);
	}
	
	public ItemPedido save(ItemPedido itemPedido) {
		return this.itemPedidoRepository.save(itemPedido);
	}

	public void saveAll(Set<ItemPedido> itemPedidos) {
		this.itemPedidoRepository.saveAll(itemPedidos);
	}
	public void saveAll(List<ItemPedido> itemPedidos) {
		this.itemPedidoRepository.saveAll(itemPedidos);
	}

}
