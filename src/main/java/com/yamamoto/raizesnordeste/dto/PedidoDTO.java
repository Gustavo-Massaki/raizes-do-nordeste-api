package com.yamamoto.raizesnordeste.dto;

import com.yamamoto.raizesnordeste.enums.CanalPedido;
import com.yamamoto.raizesnordeste.enums.FormaPagamento;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoDTO(
        @NotNull
        Long usuarioId,
        @NotNull
        Long unidadeId,
        @NotNull
        FormaPagamento formaPagamento,
        @NotNull
        CanalPedido canalPedido,
        @NotEmpty
        List<ItemPedidoDTO> itens
) {


}
