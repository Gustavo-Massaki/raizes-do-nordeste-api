package com.yamamoto.raizesnordeste.dto;

import java.time.Instant;
import java.util.List;

public record ErroPadraoDTO(
        String error,
        String message,
        List<ErroDetalheDTO> details,
        Instant timestamp,
        String path
) {
}
