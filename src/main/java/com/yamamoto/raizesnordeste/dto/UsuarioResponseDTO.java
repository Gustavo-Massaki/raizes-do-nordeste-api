package com.yamamoto.raizesnordeste.dto;

import com.yamamoto.raizesnordeste.enums.Perfil;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        Perfil perfil
) {
}
