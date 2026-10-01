package com.yamamoto.raizesnordeste.repository;

import com.yamamoto.raizesnordeste.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
