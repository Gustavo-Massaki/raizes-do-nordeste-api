package com.yamamoto.raizesnordeste.repository;

import com.yamamoto.raizesnordeste.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
