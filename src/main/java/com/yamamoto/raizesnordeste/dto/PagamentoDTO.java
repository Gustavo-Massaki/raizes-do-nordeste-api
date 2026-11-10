package com.yamamoto.raizesnordeste.dto;

import com.yamamoto.raizesnordeste.enums.ResultadoPagamento;

public record PagamentoDTO(
        Long pedidoId,
        ResultadoPagamento resultado
) {
}
