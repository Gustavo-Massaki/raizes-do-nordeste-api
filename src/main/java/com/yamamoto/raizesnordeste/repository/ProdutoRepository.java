package com.yamamoto.raizesnordeste.repository;

import com.yamamoto.raizesnordeste.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
