package com.natanjesus.cursomc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.natanjesus.cursomc.domain.Categoria;
import com.natanjesus.cursomc.domain.Produto;
import com.natanjesus.cursomc.services.CategoriaService;
import com.natanjesus.cursomc.services.ProdutoService;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	ProdutoService produtoService;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		prod1.setCategorias(Arrays.asList(cat1));
		prod2.setCategorias(Arrays.asList(cat1, cat2));
		prod3.setCategorias(Arrays.asList(cat1));
		
		this.categoriaService.saveAll(Arrays.asList(cat1, cat2));
		this.produtoService.saveAll(Arrays.asList(prod1, prod2, prod3));
		
	}

}
