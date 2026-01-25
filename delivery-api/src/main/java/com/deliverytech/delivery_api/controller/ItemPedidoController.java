package com.deliverytech.delivery_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.model.ItemPedido;

import com.deliverytech.delivery_api.service.*;

@RestController
@RequestMapping("/item-pedidos")
public class ItemPedidoController {
    
    
    private final ItemPedidoService itemPedidoService;

    
    public ItemPedidoController(ItemPedidoService itemPedidoService){
        this.itemPedidoService = itemPedidoService;
    }

    
    @GetMapping("/pedido/{pedidoId}")
    public List<ItemPedido> listarPorPedido(@PathVariable long pedidoId){
        return itemPedidoService.listarPorPedido(pedidoId);
    }
}
