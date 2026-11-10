package com.yamamoto.raizesnordeste.dto;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO(
        String produto,
        Integer quantidade,
        BigDecimal valorUnitario
) {
}
