package com.deliverytech.delivery_api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    //método de listar dados
    public List<Cliente> listarAtivos(){
        return repository.findByAtivoTrue();
    }
    //método de encontrar nomes
    public List<Cliente> buscarPorNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
            
    }

    public Cliente buscarPorId(Long id){
        return repository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Cliente não encontrado"));
    }

    public Cliente atualizar(Long id, Cliente dadosAtualizados){
        
        //busca pelo cliente pelo id
        Cliente cliente = buscarPorId(id);

        cliente.setNome(dadosAtualizados.getNome());
        cliente.setEmail(dadosAtualizados.getEmail());
        cliente.setTelefone(dadosAtualizados.getTelefone());
        cliente.setEndereco(dadosAtualizados.getEndereco());

        return repository.save(cliente);

    }

    public void desativar(long id){
        Cliente cliente = buscarPorId(id);
        cliente.setAtivo(false);
        repository.save(cliente);
    }
}
