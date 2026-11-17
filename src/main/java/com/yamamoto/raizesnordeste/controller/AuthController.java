package com.yamamoto.raizesnordeste.controller;

import com.yamamoto.raizesnordeste.dto.LoginRequestDTO;
import com.yamamoto.raizesnordeste.dto.LoginResponseDTO;
import com.yamamoto.raizesnordeste.entity.Usuario;
import com.yamamoto.raizesnordeste.security.JwtService;
import com.yamamoto.raizesnordeste.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raizesnordeste/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO dto){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(),dto.senha()));
        Usuario usuario = (Usuario) auth.getPrincipal();
        String token = jwtService.gerarToken(usuario);
        return new LoginResponseDTO(token, "Bearer", 3600, usuarioService.toResponseDTO(usuario));
    }

}
