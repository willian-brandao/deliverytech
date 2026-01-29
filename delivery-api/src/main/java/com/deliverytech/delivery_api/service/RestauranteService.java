package com.deliverytech.delivery_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.model.Restaurante;
import com.deliverytech.delivery_api.repository.RestauranteRepository;

@Service
public class RestauranteService {
    
    private final RestauranteRepository repository;

    public RestauranteService( RestauranteRepository repository){

        this.repository = repository;
    }

    // cadastro do restaurante
    public Restaurante cadastrar(Restaurante dados){
        dados.setAtivo(true);
        return repository.save(dados);
    }   

    public List<Restaurante> listarRestauranteAtivos(){
        return repository.findByAtivoTrue();
    }

    public List<Restaurante> buscarPorRestaurantesPorCategoria( String categoria){
        return repository.findByCategoriaAndAtivoTrue(categoria);
    }

    public Restaurante buscarPorId( Long  id){
        return repository.findById( id )
        .orElseThrow(()-> new IllegalArgumentException("Restaurante não ecncontrado"));
    }

    public void desativar(Long id){
        Restaurante restaurante = buscarPorId(id);
        restaurante.setAtivo(false);
        repository.save(restaurante);
    }
}

