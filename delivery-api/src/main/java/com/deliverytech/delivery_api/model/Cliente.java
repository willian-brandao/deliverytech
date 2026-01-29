package com.deliverytech.delivery_api.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/*----------------------------------------------- */



@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique= true, nullable = false)
    private String email;

    private String telefone;

    private String endereco; 

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    private boolean ativo;

    @JsonIgnore
    @OneToMany(mappedBy="cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    // vai gerar uma data toda vez que o servidor for rodado e os dados foram persistidos no banco
    @PrePersist
    public void PrePersist(){
        this.dataCadastro = LocalDateTime.now();

    }
}