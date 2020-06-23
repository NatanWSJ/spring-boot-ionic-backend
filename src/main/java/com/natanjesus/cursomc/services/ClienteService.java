package com.natanjesus.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.natanjesus.cursomc.domain.Cidade;
import com.natanjesus.cursomc.domain.Cliente;
import com.natanjesus.cursomc.domain.Endereco;
import com.natanjesus.cursomc.domain.enumeration.TipoCliente;
import com.natanjesus.cursomc.dto.ClienteDTO;
import com.natanjesus.cursomc.dto.ClienteNewDTO;
import com.natanjesus.cursomc.repository.EnderecoRepository;
import com.natanjesus.cursomc.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.natanjesus.cursomc.domain.Cliente;
import com.natanjesus.cursomc.repository.ClienteRepository;
import com.natanjesus.cursomc.services.exceptions.ObjectNotFoundException;

import javax.transaction.Transactional;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	public List<Cliente> findAll() {
		return this.clienteRepository.findAll();
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: ".concat(id.toString())
				.concat(", Tipo: ".concat(Cliente.class.getName())))
				);
	}

	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = this.clienteRepository.save(cliente);
		this.enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	public Cliente update(Integer id, Cliente cliente) {
		Cliente clienteData = this.findById(id);
		this.updateData(clienteData, cliente);
		return this.clienteRepository.save(cliente);
	}

	public void delete(Integer id) {
		this.findById(id);
		try {
			this.clienteRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma cliente que há pedidos relacionados");
		}
	}

	public void saveAll(List<Cliente> clientes) {
		this.clienteRepository.saveAll(clientes);
	}

	public Page<ClienteDTO> findPage(Integer page, Integer size, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
		Page<Cliente> clientes = this.clienteRepository.findAll(pageRequest);
		return clientes.map(cliente -> new ClienteDTO(cliente));
	}

	public Cliente fromDTO(ClienteDTO cliente) {
		return new Cliente(cliente.getId(), cliente.getNome(), cliente.getEmail(), null, null);
	}
	public Cliente fromDTO(ClienteNewDTO cliente) {
		Cliente clienteNew = new Cliente(null, cliente.getNome(), cliente.getEmail(), cliente.getDocumento(), TipoCliente.toEnum(cliente.getTipo()));
		Cidade cidade = new Cidade(cliente.getCidadeId(), null, null);
		Endereco enderecoNew = new Endereco(null, cliente.getLogradouro(), cliente.getNumero(), cliente.getComplemento(), cliente.getBairro(), cliente.getCep(), cidade, clienteNew);
		clienteNew.getEnderecos().add(enderecoNew);
		clienteNew.getTelefones().add(cliente.getTelefone1());
		if (cliente.getTelefone2() != null && !cliente.getTelefone2().isEmpty()){
			clienteNew.getTelefones().add(cliente.getTelefone2());
		}
		if (cliente.getTelefone3() != null && !cliente.getTelefone3().isEmpty()){
			clienteNew.getTelefones().add(cliente.getTelefone3());
		}
		return new Cliente(null, cliente.getNome(), cliente.getEmail(), null, null);
	}

	private void updateData(Cliente clienteData, Cliente cliente){
		clienteData.setNome(cliente.getNome());
		clienteData.setEmail(cliente.getEmail());
	}

}
