package com.yamamoto.raizesnordeste.service;

import com.yamamoto.raizesnordeste.dto.UsuarioRequestDTO;
import com.yamamoto.raizesnordeste.dto.UsuarioResponseDTO;
import com.yamamoto.raizesnordeste.entity.Usuario;
import com.yamamoto.raizesnordeste.exception.RecursoNaoEncontradoException;
import com.yamamoto.raizesnordeste.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario createUsuario(UsuarioRequestDTO dto){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setPerfil(dto.perfil());
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsuario(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id){
        return usuarioRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Usuario não encontrado"));
    }

    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUsuario(Long id, UsuarioRequestDTO dto){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("Usuario não encontrado"));
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setPerfil(dto.perfil());

        return usuarioRepository.save(usuario);
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario){
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPerfil());
    }
}
