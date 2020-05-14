package com.natanjesus.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanjesus.cursomc.domain.Estado;
import com.natanjesus.cursomc.repository.EstadoRepository;
import com.natanjesus.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	EstadoRepository estadoRepository;
	
	public List<Estado> findAll() {
		return this.estadoRepository.findAll();
	}

	public Estado findById(Integer id) {
		Optional<Estado> estado = this.estadoRepository.findById(id);
		return estado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: ".concat(id.toString())
				.concat(", Tipo: ".concat(Estado.class.getName())))
				);
	}
	
	public Estado save(Estado estado) {
		return this.save(estado);
	}
	
	public void saveAll(List<Estado> estados) {
		this.estadoRepository.saveAll(estados);
	}

}
