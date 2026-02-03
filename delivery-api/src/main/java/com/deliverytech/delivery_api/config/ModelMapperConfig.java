package com.deliverytech.delivery_api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// essa classe tem o objetivo de mapear objetos automaticamente, usado para converter Entities em DTO, e DTO em Entities. 
// Exsite para disponibilizar o ModelMapper como um Bean do Spring, permitindo mapear DTOS e Entities de forma centralizada
@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
