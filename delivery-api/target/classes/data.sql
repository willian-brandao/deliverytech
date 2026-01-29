INSERT INTO clientes (nome, email, telefone, endereco, data_cadastro, ativo) VALUES
('João Silva', 'joao@email.com', '(11) 99999-1111', 'Rua A, 123 - São Paulo/SP', CURRENT_TIMESTAMP, true),
('Maria Santos', 'maria@email.com', '(11) 99999-2222', 'Rua B, 456 - São Paulo/SP', CURRENT_TIMESTAMP, true),
('Pedro Oliveira', 'pedro@email.com', '(11) 99999-3333', 'Rua C, 789 - São Paulo/SP', CURRENT_TIMESTAMP, true);
 
 
INSERT INTO restaurantes (nome, categoria, endereco, telefone, taxa_entrega, avaliacao, ativo) VALUES
('Pizzaria Bella', 'Italiana', 'Av. Paulista, 1000 - São Paulo/SP', '(11) 3333-1111', 5.00, 4.5, true),
('Burger House', 'Hamburgueria', 'Rua Augusta, 500 - São Paulo/SP', '(11) 3333-2222', 3.50, 4.2, true),
('Sushi Master', 'Japonesa', 'Rua Liberdade, 200 - São Paulo/SP', '(11) 3333-3333', 8.00, 4.8, true);
 
 
INSERT INTO produtos (id, nome, descricao, preco, categoria, disponivel, restaurante_id) VALUES
(1, 'Pizza Margherita', 'Molho de tomate, mussarela e manjericão', 35.90, 'Pizza', true, 1),
(2, 'Pizza Calabresa', 'Molho de tomate, mussarela e calabresa', 38.90, 'Pizza', true, 1),
(3, 'Lasanha Bolonhesa', 'Lasanha tradicional com molho bolonhesa', 28.90, 'Massa', true, 1),
 
(4, 'X-Burger', 'Hambúrguer, queijo, alface e tomate', 18.90, 'Hambúrguer', true, 2),
(5, 'X-Bacon', 'Hambúrguer, queijo, bacon, alface e tomate', 22.90, 'Hambúrguer', true, 2),
 
(6, 'Batata Frita', 'Porção de batata frita crocante', 12.90, 'Acompanhamento', true, 2),
 
(7, 'Combo Sashimi', '15 peças de sashimi variado', 45.90, 'Sashimi', true, 3),
(8, 'Hot Roll Salmão', '8 peças de hot roll de salmão', 32.90, 'Hot Roll', true, 3);
 
 
 
INSERT INTO pedidos (id, numero_pedido, data_pedido, status, valor_total, observacoes, cliente_id, restaurante_id) VALUES
(1, 'PED1234567890', CURRENT_TIMESTAMP, 'PENDENTE', 64.80, 'Sem cebola na pizza', 1, 1),
(2, 'PED1234567891', CURRENT_TIMESTAMP, 'CONFIRMADO', 41.80, '', 2, 2),
(3, 'PED1234567892', CURRENT_TIMESTAMP, 'ENTREGUE', 78.80, 'Wasabi à parte', 3, 3);
 
 
INSERT INTO item_pedidos (quantidade, preco_unitario, subtotal, pedido_id, produto_id) VALUES
(1, 35.90, 35.90, 1, 1),
(1, 28.90, 28.90, 1, 3),
 
(1, 22.90, 22.90, 2, 5),
(1, 18.90, 18.90, 2, 4),
 
(1, 45.90, 45.90, 3, 7),
(1, 32.90, 32.90, 3, 8);
 

 -------------------------------------- query de testes ---------------------------------

// total de vendas por restaurantes

select r.nome,
COALESCE(SUM(ip.subtotal), 0) as total_vendas from restaurantes r
join pedidos p ON p.restaurante_id = r.id
join item_pedidos ip on ip.pedido_id = p.id
group by r.nome;

// filtrar pedidos acima de 100 reais
select p.id,
          p.data_pedido,
          SUM(ip.subtotal) AS total_pedido
from  pedidos p
join item_pedidos ip on ip.pedido_id = p.id
group by p.id, p.data_pedido
having sum(ip.subtotal) > 100;

