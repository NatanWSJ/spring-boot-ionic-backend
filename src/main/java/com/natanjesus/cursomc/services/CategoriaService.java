package com.natanjesus.cursomc.services;

import com.natanjesus.cursomc.domain.Categoria;
import com.natanjesus.cursomc.dto.CategoriaDTO;
import com.natanjesus.cursomc.repository.CategoriaRepository;
import com.natanjesus.cursomc.services.exceptions.DataIntegrityException;
import com.natanjesus.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;

	public List<CategoriaDTO> findAll() {
		List<Categoria> list = this.categoriaRepository.findAll();
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}

	public Categoria findById(Integer id) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: ".concat(id.toString())
				.concat(", Tipo: ".concat(Categoria.class.getName())))
				);
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return this.categoriaRepository.save(categoria);
	}

	public Categoria update(Integer id, Categoria categoria) {
		Categoria categoriaData = this.findById(id);
		this.updateData(categoriaData, categoria);
		return this.categoriaRepository.save(categoria);
	}
	
	public void delete(Integer id) {
		this.findById(id);
		try {
			this.categoriaRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public void saveAll(List<Categoria> categorias) {
		this.categoriaRepository.saveAll(categorias);
	}

	public Page<CategoriaDTO> findPage(Integer page, Integer size, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
		Page<Categoria> categorias = this.categoriaRepository.findAll(pageRequest);
		return categorias.map(categoria -> new CategoriaDTO(categoria));
	}

	public Categoria fromDTO(CategoriaDTO categoria) {
		return new Categoria(categoria.getId(), categoria.getNome());
	}

	private void updateData(Categoria categoriaData, Categoria categoria){
		categoriaData.setNome(categoria.getNome());
	}
	
}
