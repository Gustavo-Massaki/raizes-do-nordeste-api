package com.yamamoto.raizesnordeste.dto;

public record LoginResponseDTO(
        String accessToken,
        String tokenType,
        long expiresIn,
        UsuarioResponseDTO user
) {
}
