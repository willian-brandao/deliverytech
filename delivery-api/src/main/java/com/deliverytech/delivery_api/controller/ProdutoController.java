package com.deliverytech.delivery_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.service.ProdutoService;
import com.deliverytech.delivery_api.model.*;
import com.deliverytech.delivery_api.repository.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    public final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping("/{restauranteId}")
    public ResponseEntity<Produto> cadastrar(@PathVariable Restaurante restauranteId, @RequestBody Produto produto){
        return ResponseEntity.status(201).body(service.cadastrar(produto));
    }
}

