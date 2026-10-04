package com.yamamoto.raizesnordeste.service;

import com.yamamoto.raizesnordeste.entity.Usuario;
import com.yamamoto.raizesnordeste.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario createUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsuario(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id){
        return usuarioRepository.findById(id).orElseThrow();
    }

    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUsuario(Long id, Usuario updatedUsuario){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setNome(updatedUsuario.getNome());
        usuario.setEmail(updatedUsuario.getEmail());
        usuario.setSenha(updatedUsuario.getSenha());
        usuario.setPerfil(updatedUsuario.getPerfil());

        return usuarioRepository.save(usuario);
    }

}
