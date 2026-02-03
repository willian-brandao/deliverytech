package com.deliverytech.delivery_api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.dto.responses.ClienteResponseDTO;
import com.deliverytech.delivery_api.exceptions.BusinessException;
import com.deliverytech.delivery_api.exceptions.EntityNotFoundException;
import com.deliverytech.delivery_api.model.Cliente;
import com.deliverytech.delivery_api.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class ClienteService {
    
    private ClienteRepository repository;
    private ModelMapper mapper;

    public ClienteService(ClienteRepository repository, ModelMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    //método de cadastro 
    public ClienteResponseDTO cadastrar(Cliente dto){
        
        if( repository.existsByEmail( dto.getEmail() )){
            throw new BusinessException("E-mail já cadastrado");
        }

        Cliente cliente = mapper.map(dto, Cliente.class);

        cliente.setAtivo(true);
        cliente.setDataCadastro(LocalDateTime.now());
        
        Cliente salvo = repository.save(cliente);
        return mapper.map(salvo, ClienteResponseDTO.class);
    }


    //método de listar dados
    public List<ClienteResponseDTO> listarAtivos(){
        return repository.findByAtivoTrue()
        .stream()
        .map(clientes -> mapper.map(clientes, ClienteResponseDTO.class))
        .toList();
    }


    //método de encontrar nomes
    public List<ClienteResponseDTO> buscarPorNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome)
        .stream()
        .map(cliente -> mapper.map(cliente, ClienteResponseDTO.class))
        .toList();
            
    }

    public ClienteResponseDTO buscarPorId(Long id){
        
        Cliente cliente = repository.findById(id)
        .orElseThrow(()-> new EntityNotFoundException("Cliente não encontrado"));
        return mapper.map(cliente, ClienteResponseDTO.class);
    }
/* 
    public Cliente atualizar(Long id, Cliente dadosAtualizados){
        
        //busca pelo cliente pelo id
        Cliente cliente = buscarPorId(id);

        cliente.setNome(dadosAtualizados.getNome());
        cliente.setEmail(dadosAtualizados.getEmail());
        cliente.setTelefone(dadosAtualizados.getTelefone());
        cliente.setEndereco(dadosAtualizados.getEndereco());

        return repository.save(cliente);

    }
*/

    @Transactional
    public ClienteResponseDTO toggleAtivo(long id){
        Cliente cliente = repository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Cliente não encontrado"));

        cliente.setAtivo(!cliente.isAtivo());
        return mapper.map(cliente, ClienteResponseDTO.class);
    }
}


/*
//método de listar dadosSS
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
*/