package com.deliverytech.delivery_api.controller;


import com.deliverytech.delivery_api.service.*;

import jakarta.validation.Valid;

import com.deliverytech.delivery_api.model.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
   private final ClienteService service;
   
   public ClienteController(ClienteService service){
        this.service = service;
   }

   // o método invoca o repository salvar no banco de dados e caso seja bem sucedido, ele retorna o código de sucesso
   @PostMapping
   public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteDTO cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(cliente));
   }

   @GetMapping("/listar")
   public List<ClienteResponseDTO> listar(){
        return service.listarAtivos();
   }
   
   @GetMapping("/{id}")
   public ClienteResponseDTO buscar(@PathVariable Long id){
        return service.buscarPorId(id);
   }

/* 
   @PutMapping("/{id}")
   public Cliente atualizar(@PathVariable long id, @RequestBody Cliente novosDados){
        return service.atualizar(id, novosDados);
   }
*/

   @PutMapping("/{id}/toggle")
   public ResponseEntity<ClienteResponseDTO> toggleAtivo(@PathVariable long id){
     return ResponseEntity.ok(service.toggleAtivo(id));
   }

}


/*

primeira versão, antes do DTO
// o método invoca o repository salvar no banco de dados e caso seja bem sucedido, ele retorna o código de sucesso
   @PostMapping
   public ResponseEntity<Cliente> cadastrar( @RequestBody Cliente cliente){
        return ResponseEntity.status(201).body(service.cadastrar(cliente));
   }

   @GetMapping("/listar")
   public List<Cliente> listar(){
        return service.listarAtivos();
   }
   
   @GetMapping("/{id}")
   public Cliente buscar(@PathVariable Long id){
        return service.buscarPorId(id);
   }

   @PutMapping("/{id}")
   public Cliente atualizar(@PathVariable long id, @RequestBody Cliente novosDados){
        return service.atualizar(id, novosDados);
   }

   @PutMapping("/desativar/{id}")
   public ResponseEntity<Void> desativar(@PathVariable long id){
        service.desativar(id);
        return ResponseEntity.noContent().build();

   }

*/