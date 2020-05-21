package com.natanjesus.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanjesus.cursomc.domain.Pagamento;
import com.natanjesus.cursomc.repository.PagamentoRepository;
import com.natanjesus.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PagamentoService {
	
	@Autowired
	PagamentoRepository pagamentoRepository;

	public List<Pagamento> findAll() {
		return this.pagamentoRepository.findAll();
	}

	public Pagamento findById(Integer id) {
		Optional<Pagamento> pagamento = this.pagamentoRepository.findById(id);
		return pagamento.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: ".concat(id.toString())
				.concat(", Tipo: ".concat(Pagamento.class.getName())))
				);
	}
	
	public Pagamento save(Pagamento pagamento) {
		return this.save(pagamento);
	}
	
	public void saveAll(List<Pagamento> pagamentos) {
		this.pagamentoRepository.saveAll(pagamentos);
	}

}
