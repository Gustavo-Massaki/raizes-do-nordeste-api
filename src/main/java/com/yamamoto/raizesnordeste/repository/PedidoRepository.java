package com.yamamoto.raizesnordeste.repository;

import com.yamamoto.raizesnordeste.entity.Pedido;
import com.yamamoto.raizesnordeste.enums.CanalPedido;
import com.yamamoto.raizesnordeste.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCanalPedido(CanalPedido canalPedido);
    List<Pedido> findByStatus(StatusPedido statusPedido);
    List<Pedido> findByCanalPedidoAndStatus(CanalPedido canalPedido, StatusPedido statusPedido);
}
