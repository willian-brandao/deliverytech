package com.deliverytech.delivery_api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.model.Restaurante;
import com.deliverytech.delivery_api.repository.ClienteRepository;
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
        PedidoRepository pedidoRepository
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

            //salvando os objetos
            restauranteRepository.saveAll(Arrays.asList(restaurante_1, restaurante_2));

        }
    }

}
