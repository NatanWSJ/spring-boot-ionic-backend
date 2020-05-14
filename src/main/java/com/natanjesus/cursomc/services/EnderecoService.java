package com.natanjesus.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanjesus.cursomc.domain.Endereco;
import com.natanjesus.cursomc.repository.EnderecoRepository;
import com.natanjesus.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;

	public List<Endereco> findAll() {
		return this.enderecoRepository.findAll();
	}

	public Endereco findById(Integer id) {
		Optional<Endereco> endereco = this.enderecoRepository.findById(id);
		return endereco.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: ".concat(id.toString())
				.concat(", Tipo: ".concat(Endereco.class.getName())))
				);
	}
	
	public Endereco save(Endereco endereco) {
		return this.save(endereco);
	}
	
	public void saveAll(List<Endereco> enderecos) {
		this.enderecoRepository.saveAll(enderecos);
	}

}
