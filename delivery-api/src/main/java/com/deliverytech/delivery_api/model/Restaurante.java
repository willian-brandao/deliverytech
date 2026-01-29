package com.deliverytech.delivery_api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Collate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Getter
@Setter
@Entity
@Table(name= "restaurantes")

public class Restaurante{
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String categoria;

    private String endereco;

    private String telefone;

    private BigDecimal avaliacao;



    @Column(name = "taxa_entrega")
    private BigDecimal taxaEntrega;

    private boolean ativo;

    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)
    
    @JsonIgnore //linha adicionada para teste com o banco
    private List<Produto> produtos = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)
    
    @JsonIgnore //linha adicionada para teste com o banco
    private List<Pedido> pedidos = new ArrayList<>();
}
