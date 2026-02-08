package com.deliverytech.delivery_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.service.*;
import com.deliverytech.delivery_api.dto.requests.RestauranteDTO;
import com.deliverytech.delivery_api.dto.responses.RestauranteResponseDTO;
import com.deliverytech.delivery_api.model.*;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    
    private final RestauranteService service;

    public RestauranteController(RestauranteService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RestauranteResponseDTO> cadastrar(@RequestBody RestauranteDTO dados){
        RestauranteResponseDTO response = service.cadastrar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<RestauranteResponseDTO>> listar(){
        return ResponseEntity.ok(service.listarRestauranteAtivos());
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponseDTO> buscarPorId(@PathVariable long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("categoria/{categoria}")
    public List<RestauranteResponseDTO> buscarPorCategoria(@RequestParam String categoria){
        return service.buscarPorRestaurantesPorCategoria(categoria);
    }

    @DeleteMapping("/{id}/toggle")
    public ResponseEntity<RestauranteResponseDTO> toggle(@PathVariable long id){
        return ResponseEntity.ok(service.toggle(id));
    }


}