package com.deliverytech.delivery_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.model.Pedido;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    private final PedidoService pedidoService;
    

}
