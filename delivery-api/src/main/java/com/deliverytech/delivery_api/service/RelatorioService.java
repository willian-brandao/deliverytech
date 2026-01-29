package com.deliverytech.delivery_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.repository.PedidoRepository;
import com.deliverytech.delivery_api.dto.TotalDeVendasPorRestauranteDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    
    private final PedidoRepository pedidoRepository;

    public List<TotalDeVendasPorRestauranteDTO> totalDeVendasPorRestaurante(){
        return pedidoRepository.totalDeVendasPorRestaurante();
    }

    public List<Object[]> rankingClientes(){
        return pedidoRepository.rankingClientes();
    } 
}
