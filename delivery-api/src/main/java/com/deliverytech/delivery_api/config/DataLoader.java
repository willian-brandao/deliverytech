package com.deliverytech.delivery_api.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.deliverytech.delivery_api.enums.StatusPedidos;
import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.model.ItemPedido;
import com.deliverytech.delivery_api.model.Pedido;
import com.deliverytech.delivery_api.model.Produto;
import com.deliverytech.delivery_api.model.Restaurante;
import com.deliverytech.delivery_api.repository.ClienteRepository;
import com.deliverytech.delivery_api.repository.ItemPedidoRepository;
import com.deliverytech.delivery_api.repository.PedidoRepository;
import com.deliverytech.delivery_api.repository.ProdutoRepository;
import com.deliverytech.delivery_api.repository.RestauranteRepository;

@Configuration
public class DataLoader {
    @Bean
    public CommandLineRunner initData(
        ClienteRepository clienteRepository,
        RestauranteRepository restauranteRepository,
        ProdutoRepository produtoRepository,
        PedidoRepository pedidoRepository,
        ItemPedidoRepository itemPedidoRepository

    ){
        return args ->{

            System.out.println("Iniciando dados");

            Cliente cliente1 = new Cliente();
            cliente1.setNome("João das Neves");
            cliente1.setEmail("joao_daneves@email.com");
            cliente1.setTelefone("2299999999");
            cliente1.setEndereco("Rua das flores, 132, São Paulo, SP");
            cliente1.setAtivo(true);


            Cliente cliente2 = new Cliente();
            cliente2.setNome("Genecilda Silva");
            cliente2.setEmail("genecilda@email.com");
            cliente2.setTelefone("22995838483");
            cliente2.setEndereco("Rua das conchas,432, Rio das Ostras, RJ");
            cliente2.setAtivo(true);


            //salvando os objetos
            clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));


            Restaurante restaurante_1 = new Restaurante();
            restaurante_1.setNome("Casa do Sabor");
            restaurante_1.setCategoria("Frutos do mar");
            restaurante_1.setEndereco("Rua do armador, 234, Rio de janeiro, RJ");
            restaurante_1.setTelefone("22 2345-2323");
            restaurante_1.setAvaliacao(new java.math.BigDecimal("7.8"));
            restaurante_1.setTaxaEntrega(new java.math.BigDecimal("15"));
            restaurante_1.setAtivo(true);

            Restaurante restaurante_2 = new Restaurante();

            restaurante_2.setNome("Pizza do Amor");
            restaurante_2.setCategoria("Pizzaria");
            restaurante_2.setEndereco("Av. Dos Freitas, 23, Campos dos Goytacazes, RJ");
            restaurante_2.setTelefone("22 2353-2376");
            restaurante_2.setAvaliacao(new java.math.BigDecimal("8.5"));
            restaurante_2.setTaxaEntrega(new java.math.BigDecimal("12.99"));
            restaurante_2.setAtivo(true);

            Restaurante restaurante_3 = new Restaurante();
            restaurante_3.setNome("Creperia Lábios Molhados");
            restaurante_3.setCategoria("Creperia");
            restaurante_3.setEndereco("Travessar do lugar, 45, Nova Friburgo, RJ");
            restaurante_3.setTelefone("22 2323-5645");
            restaurante_3.setAvaliacao(new java.math.BigDecimal("7.5"));
            restaurante_3.setTaxaEntrega(new java.math.BigDecimal("13.99"));
            restaurante_3.setAtivo(true);


            //salvando os objetos
            restauranteRepository.saveAll(Arrays.asList(restaurante_1, restaurante_2, restaurante_3));


            Produto produto1 = new Produto();
            produto1.setNome("Porção de Camarão Empanado");
            produto1.setDescricao("Deliciosa e generosa porção de camarão");
            produto1.setPreco(new BigDecimal("35.00"));
            produto1.setCategoria("frutos do mar");
            produto1.setDisponivel(true);
            produto1.setRestaurante(restaurante_1);


            Produto produto2 = new Produto();
            produto2.setNome("Pizza Marguerita");
            produto2.setDescricao("Deliciosa pizza");
            produto2.setPreco(new BigDecimal("45.00"));
            produto2.setCategoria("pizza");
            produto2.setDisponivel(true);
            produto2.setRestaurante(restaurante_2);

            Produto produto3 = new Produto();
            produto3.setNome("Salgadsos Sortidos");
            produto3.setDescricao("Porção de salgados");
            produto3.setPreco(new BigDecimal("45.00"));
            produto3.setCategoria("Salgados");
            produto3.setDisponivel(true);
            produto3.setRestaurante(restaurante_3);


            Produto produto4 = new Produto();
            produto4.setNome("Pizza Doce de Chocolate");
            produto4.setDescricao("Deliciosa pizza");
            produto4.setPreco(new BigDecimal("55.00"));
            produto4.setCategoria("pizza");
            produto4.setDisponivel(true);
            produto4.setRestaurante(restaurante_2);


            Produto produto5 = new Produto();
            produto5.setNome("Crepe de Queijo e Presunto");
            produto5.setDescricao("Delicioso crepe");
            produto5.setPreco(new BigDecimal("32.00"));
            produto5.setCategoria("crepe");
            produto5.setDisponivel(true);
            produto5.setRestaurante(restaurante_2);

            
            List<Produto> produtos = new ArrayList<>();

            produtos.add(produto1);
            produtos.add(produto2);
            produtos.add(produto3);
            produtos.add(produto4);
            produtos.add(produto5);

            produtoRepository.saveAll(produtos);

            
            Pedido pedido1 = new Pedido();
            pedido1.setCliente(cliente1);
            pedido1.setRestaurante(restaurante_1);
            pedido1.setStatus(StatusPedidos.PENDENTE);
            pedido1.setEnderecoEntrega(cliente1.getEndereco());
            pedido1.setValorTotal(BigDecimal.ZERO);
            pedido1.setTaxaEntrega(restaurante_1.getTaxaEntrega());


            Pedido pedido2 = new Pedido();
            pedido2.setCliente(cliente2);
            pedido2.setRestaurante(restaurante_2);
            pedido2.setStatus(StatusPedidos.PENDENTE);
            pedido2.setEnderecoEntrega(cliente2.getEndereco());
            pedido2.setValorTotal(BigDecimal.ZERO);
            pedido2.setTaxaEntrega(restaurante_2.getTaxaEntrega());
            
            Pedido pedido3 = new Pedido();
            pedido3.setCliente(cliente2);
            pedido3.setRestaurante(restaurante_3);
            pedido3.setStatus(StatusPedidos.PENDENTE);
            pedido3.setEnderecoEntrega(cliente2.getEndereco());
            pedido3.setValorTotal(BigDecimal.ZERO);
            pedido3.setTaxaEntrega(restaurante_3.getTaxaEntrega()); 
        
            pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2, pedido3));

            
            ItemPedido item1 = new ItemPedido();
            item1.setProduto(produto1);
            item1.setPedido(pedido1);
            item1.setQuantidade(2);
            item1.setPrecoUnitario(produto1.getPreco());
            item1.setSubtotal(produto1.getPreco().multiply(BigDecimal.valueOf((item1.getQuantidade()))));

            ItemPedido item2 = new ItemPedido();
            item2.setProduto(produto2);
            item2.setPedido(pedido2);
            item2.setQuantidade(5);
            item2.setPrecoUnitario(produto2.getPreco());
            item2.setSubtotal(produto1.getPreco().multiply(BigDecimal.valueOf((item2.getQuantidade()))));

            itemPedidoRepository.saveAll(Arrays.asList(item1, item2));

            System.out.println("Dados carregados com sucesso");
        };
    }

}
