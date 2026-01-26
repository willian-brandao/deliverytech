package com.deliverytech.delivery_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deliverytech.delivery_api.enums.StatusPedidos;
import com.deliverytech.delivery_api.model.Pedido;
import com.deliverytech.delivery_api.dto.*;


public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    List<Pedido> findByClienteId(long clienteId);

    List<Pedido> findByStatus(StatusPedidos status);

    
    @Query("""
            SELECT p FROM Pedido p
            WHERE p.dataPedido BETWEEN :inicio AND :fim
    """)
    List<Pedido> findByDateTime(
        @Param("inicio") LocalDateTime inicio,
        @Param("fim") LocalDateTime fim
    );

    
    @Query( value = """
            select com.deliverytech.delivery_api.dto.TotalDeVendasPorRestauranteDTO(
            r.NOME,
            coalesce(sum(ip.SUBTOTAL), 0) as total_por_restaurante
            )
            from RESTAURANTE r
            join PEDIDOS p on p.RESTAURANTE_ID = r.ID
            join ITENS_PEDIDO ip on ip.PEDIDO_ID = p.ID
            group  by r.nome
    """, nativeQuery = true )
    List<TotalDeVendasPorRestauranteDTO> totalDeVendasPorRestauranteDTO();

} 

/*

@Query(""" 
        SELECT p FROM Pedido p 
        WHERE p.dataPedido BETWEEN :inicio AND :fim
    """)

    List<Pedido> findByDateTime(
        @Param("inicio") LocalDateTime inicio,
        @Param("fim") LocalDateTime fim
    );
*/