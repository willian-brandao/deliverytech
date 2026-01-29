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

    

    @Query("""
        select new com.deliverytech.delivery_api.dto.TotalDeVendasPorRestauranteDTO(
        r.nome,
        coalesce(sum(ip.subtotal), 0)
        )
        from Restaurante r
        join r.pedidos p
        join p.itens ip
        group by r.nome
    """)
    List<TotalDeVendasPorRestauranteDTO> totalDeVendasPorRestaurante();

    @Query(
        value = """
                select c.nome as cliente, count(p.id) as total_pedidos
                from pedidos p 
                join clientes c on c.id = p.cliente_id 
                group by c.nome
                order by total_pedidos desc 
        """, nativeQuery = true)
    List<Object[]> rankingClientes();

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