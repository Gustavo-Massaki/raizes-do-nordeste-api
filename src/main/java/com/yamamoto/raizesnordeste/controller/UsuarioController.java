package com.yamamoto.raizesnordeste.controller;

import com.yamamoto.raizesnordeste.dto.UsuarioRequestDTO;
import com.yamamoto.raizesnordeste.dto.UsuarioResponseDTO;
import com.yamamoto.raizesnordeste.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raizesnordeste/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO createUsuario(@RequestBody @Valid UsuarioRequestDTO usuario){
        return usuarioService.toResponseDTO(usuarioService.createUsuario(usuario));
    }

    @GetMapping
    public List<UsuarioResponseDTO> getAllUsuario(){
        return usuarioService.getAllUsuario().stream().map(usuario -> usuarioService.toResponseDTO(usuario)).toList();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO getUsuarioById(@PathVariable Long id){
        return usuarioService.toResponseDTO(usuarioService.getUsuarioById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO updateUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO usuario){
        return usuarioService.toResponseDTO(usuarioService.updateUsuario(id, usuario));
    }

}
