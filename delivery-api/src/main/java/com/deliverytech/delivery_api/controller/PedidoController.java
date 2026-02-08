package com.deliverytech.delivery_api.controller;

//importação de biblioteca do java
import java.util.List;

import org.springframework.http.ResponseEntity;
// importação das bibliotecas do spring
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// importação das arquivos externos
import com.deliverytech.delivery_api.enums.StatusPedidos;
import com.deliverytech.delivery_api.model.*;
import com.deliverytech.delivery_api.service.*;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/pedidos")

public class PedidoController {
    
    private final PedidoService pedidoService;
    
    //construtor
    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody @Valid PedidoDTO dto){
        return ResponseEntity.ok(pedidoService.criarPedido(dto));
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<PedidoResponseDTO> confirmar(@PathVariablelong id){
        return ResponseEntity.ok(pedidoService.confirmarPedido(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(@PathVariable long id){
        return ResponseEntity.ok(pedidoService.atualizarPedido(id));
    }
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoResponseDTO>> listarItensPorCliente(@PathVariable long clienteId){
        return ResponseEntity.ok(pedidoService.listaPorCliente(clienteId));
    }

    @PutMapping("/{id}/cancelar")
    public(ResponseEntity<PedidoResponseDTO) cancelarPedido(@PathVariable long id){
        return ResponseEntity.ok(pedidoService.criarPedido(id));        
    }
}

/*
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

*/