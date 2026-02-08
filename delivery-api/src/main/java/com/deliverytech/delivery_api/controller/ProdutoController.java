package com.deliverytech.delivery_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.service.ProdutoService;

import jakarta.validation.Valid;

import com.deliverytech.delivery_api.dto.requests.ProdutoDTO;
import com.deliverytech.delivery_api.dto.responses.ProdutoResponseDTO;
import com.deliverytech.delivery_api.model.*;
import com.deliverytech.delivery_api.repository.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    public final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

   @PostMapping("/restaurante/{restauranteId}")
   public ResponseEntity<ProdutoResponseDTO> cadastrar(@PathVariable long restauranteId, @RequestBody @Valid ProdutoDTO produto){
        ProdutoResponseDTO resposta = produtoService.cadastrar(restauranteId,produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
   }

   @GetMapping("/restaurante/{restauranteId}")
   public ResponseEntity<List<ProdutoResponseDTO>> listarPorRestaurante(@PathVariable long restauranteId){
        return ResponseEntity.ok(produtoService.listarPorRestaurante(restauranteId));
   }


   @PatchMapping("/{produtoId}/disponibilidade")
   public ResponseEntity<ProdutoResponseDTO> toggleDisponibilidade(@PathVariable long produtoId){
        return ResponseEntity.ok(produtoService.toggleDisponibilidade(produtoId));
   }
}



/*
 @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable long id){
        return produtoService.buscarPorId(id);
    }

    @PostMapping("/{restauranteId}")
    public ResponseEntity<Produto> cadastrar(@PathVariable long restauranteId, @RequestBody Produto produto){
        return ResponseEntity.status(201).body(produtoService.cadastrar(restauranteId, produto));
    }

    @PostMapping("/restaurante/{restauranteId}")
    public List<Produto> listarPorRestaurante(@PathVariable long restauranteId){
        return produtoService.listarPorRestaurante(restauranteId);
    }
 */