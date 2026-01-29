package com.deliverytech.delivery_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.dto.TotalDeVendasPorRestauranteDTO;
import com.deliverytech.delivery_api.service.RelatorioService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {
    
    private final RelatorioService relatorioService;

    @GetMapping("/total-vendas-por-restaurante")
    public List<TotalDeVendasPorRestauranteDTO> totalDeVendasPorRestaurante(){
        return relatorioService.totalDeVendasPorRestaurante();
    }

    @GetMapping ("/ranking-clientes")
    public List<Object[]> rankingClientes(){
        return relatorioService.rankingClientes();
    }
}

