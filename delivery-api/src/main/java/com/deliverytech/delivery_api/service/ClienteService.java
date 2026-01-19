package com.deliverytech.delivery_api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class ClienteService {
    
    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }

    //método de cadastro 
    public Cliente cadastrar(Cliente cliente){
        
        if( repository.existsByEmail( cliente.getEmail() )){
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        cliente.setAtivo(true);
        cliente.setDataCadastro(LocalDateTime.now());
        return repository.save(cliente);
    }

    
}
