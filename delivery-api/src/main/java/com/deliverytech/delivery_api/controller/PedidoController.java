package com.deliverytech.delivery_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.enums.StatusPedidos;
import com.deliverytech.delivery_api.model.*;
import com.deliverytech.delivery_api.service.*;


@RestController
@RequestMapping("/pedidos")

public class PedidoController {
    
    private final PedidoService pedidoService;
    
    //construtor
    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @PostMapping("/criar")
    public Pedido criarPedido(@RequestParam Long clienteId, @RequestParam long restauranteId){
        return pedidoService.criarPedido(clienteId, restauranteId);
    }

    @PutMapping("/{id}/status")
    public Pedido atualizarPedido(@PathVariable long id, @RequestParam StatusPedidos status){
        return pedidoService.atualizarPedido(id, status);
    }

    @GetMapping("/cliente/{id}")
    public List<Pedido> listaPorCliente(@PathVariable long id){
        return pedidoService.listaPorCliente(id);
    }
}
