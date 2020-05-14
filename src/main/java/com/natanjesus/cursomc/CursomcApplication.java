package com.natanjesus.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.natanjesus.cursomc.domain.Categoria;
import com.natanjesus.cursomc.domain.Cidade;
import com.natanjesus.cursomc.domain.Cliente;
import com.natanjesus.cursomc.domain.Endereco;
import com.natanjesus.cursomc.domain.Estado;
import com.natanjesus.cursomc.domain.Produto;
import com.natanjesus.cursomc.domain.enumeration.TipoCliente;
import com.natanjesus.cursomc.services.CategoriaService;
import com.natanjesus.cursomc.services.CidadeService;
import com.natanjesus.cursomc.services.ClienteService;
import com.natanjesus.cursomc.services.EnderecoService;
import com.natanjesus.cursomc.services.EstadoService;
import com.natanjesus.cursomc.services.ProdutoService;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	EstadoService estadoService;
	
	@Autowired
	CidadeService cidadeService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	EnderecoService enderecoService;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		this.categoriaService.saveAll(Arrays.asList(cat1, cat2));
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		prod1.setCategorias(Arrays.asList(cat1));
		prod2.setCategorias(Arrays.asList(cat1, cat2));
		prod3.setCategorias(Arrays.asList(cat1));
		
		this.produtoService.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		this.estadoService.saveAll(Arrays.asList(est1, est2));
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.setCidades(Arrays.asList(cid1));
		est2.setCidades(Arrays.asList(cid2, cid3));
		
		this.cidadeService.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cid1, cli1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cid2, cli1);	
		
		cli1.setEnderecos(Arrays.asList(end1, end2));
		
		this.clienteService.saveAll(Arrays.asList(cli1));
		this.enderecoService.saveAll(Arrays.asList(end1, end2));
		
	}

}
