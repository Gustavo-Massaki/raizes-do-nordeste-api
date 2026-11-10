package com.yamamoto.raizesnordeste.dto;

import com.yamamoto.raizesnordeste.enums.CanalPedido;
import com.yamamoto.raizesnordeste.enums.FormaPagamento;
import com.yamamoto.raizesnordeste.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        String nomeUsuario,
        String unidade,
        StatusPedido status,
        FormaPagamento formaPagamento,
        CanalPedido canalPedido,
        BigDecimal valorTotal,
        LocalDateTime dataHora,
        List<ItemPedidoResponseDTO> itens
) {
}
