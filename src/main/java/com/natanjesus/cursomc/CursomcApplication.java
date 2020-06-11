package com.natanjesus.cursomc;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
import com.natanjesus.cursomc.domain.ItemPedido;
import com.natanjesus.cursomc.domain.Pagamento;
import com.natanjesus.cursomc.domain.PagamentoComBoleto;
import com.natanjesus.cursomc.domain.PagamentoComCartao;
import com.natanjesus.cursomc.domain.Pedido;
import com.natanjesus.cursomc.domain.Produto;
import com.natanjesus.cursomc.domain.enumeration.EstadoPagamento;
import com.natanjesus.cursomc.domain.enumeration.TipoCliente;
import com.natanjesus.cursomc.services.CategoriaService;
import com.natanjesus.cursomc.services.CidadeService;
import com.natanjesus.cursomc.services.ClienteService;
import com.natanjesus.cursomc.services.EnderecoService;
import com.natanjesus.cursomc.services.EstadoService;
import com.natanjesus.cursomc.services.ItemPedidoService;
import com.natanjesus.cursomc.services.PagamentoService;
import com.natanjesus.cursomc.services.PedidoService;
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
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	PagamentoService pagamentoService;
	
	@Autowired
	ItemPedidoService itemPedidoService;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Eletrônicos");
		Categoria cat4 = new Categoria(null, "Jardinagem");
		Categoria cat5 = new Categoria(null, "Perfumaria");
		
		this.categoriaService.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
		
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		cli1.setPedidos(Arrays.asList(ped1, ped2));
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		this.pedidoService.saveAll(Arrays.asList(ped1, ped2));
		this.pagamentoService.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		prod1.getItens().addAll(Arrays.asList(ip1));
		prod2.getItens().addAll(Arrays.asList(ip2));
		prod3.getItens().addAll(Arrays.asList(ip3));
		
		this.itemPedidoService.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
