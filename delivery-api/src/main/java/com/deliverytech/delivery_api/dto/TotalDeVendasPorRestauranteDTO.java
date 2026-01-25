package com.deliverytech.delivery_api.dto;

import java.math.BigDecimal;

public class TotalDeVendasPorRestauranteDTO {
    
    private String restaurante;
    private BigDecimal totalVendas;

    public TotalDeVendasPorRestauranteDTO( String restaurante, BigDecimal totalVendas){
        this.restaurante = restaurante;
        this.totalVendas = totalVendas;
    }

    public String getRestaurante(){
        return restaurante;
    }

    public BigDecimal getTotalVendas(){
        return totalVendas;
    }
}
