package com.natanjesus.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natanjesus.cursomc.domain.Cidade;
import com.natanjesus.cursomc.repository.CidadeRepository;
import com.natanjesus.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	public List<Cidade> findAll() {
		return this.cidadeRepository.findAll();
	}

	public Cidade findById(Integer id) {
		Optional<Cidade> cidade = this.cidadeRepository.findById(id);
		return cidade.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: ".concat(id.toString())
				.concat(", Tipo: ".concat(Cidade.class.getName())))
				);
	}
	
	public Cidade save(Cidade cidade) {
		return this.save(cidade);
	}
	
	public void saveAll(List<Cidade> cidades) {
		this.cidadeRepository.saveAll(cidades);
	}

}
