package com.natanjesus.cursomc.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.natanjesus.cursomc.domain.ItemPedido;
import com.natanjesus.cursomc.domain.PagamentoComBoleto;
import com.natanjesus.cursomc.domain.enumeration.EstadoPagamento;
import com.natanjesus.cursomc.repository.ItemPedidoRepository;
import com.natanjesus.cursomc.repository.PagamentoRepository;
import com.natanjesus.cursomc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanjesus.cursomc.domain.Pedido;
import com.natanjesus.cursomc.repository.PedidoRepository;
import com.natanjesus.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoService pagamentoService;

	@Autowired
	private ItemPedidoService itemPedidoService;

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

	@Transactional
	public Pedido insert(Pedido pedido) {

		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagTo = (PagamentoComBoleto) pedido.getPagamento();
			this.boletoService.preencherPagamentoComBoleto(pagTo, pedido.getInstante());
		}

		pedido = this.pedidoRepository.save(pedido);
		this.pagamentoService.save(pedido.getPagamento());

		for (ItemPedido item : pedido.getItens()) {
			item.setDesconto(0.0);
			item.setPreco(this.produtoService.findById(item.getProduto().getId()).getPreco());
			item.setPedido(pedido);
		}
		this.itemPedidoService.saveAll(pedido.getItens());

		return pedido;

	}

}
