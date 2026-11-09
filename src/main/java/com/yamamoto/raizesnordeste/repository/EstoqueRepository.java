package com.yamamoto.raizesnordeste.repository;

import com.yamamoto.raizesnordeste.entity.Estoque;
import com.yamamoto.raizesnordeste.entity.Produto;
import com.yamamoto.raizesnordeste.entity.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    boolean existsByUnidadeAndProduto(Unidade unidade, Produto produto);

    boolean existsByUnidadeAndProdutoAndIdNot(Unidade unidade, Produto produto, Long id);

    Optional<Estoque> findByUnidadeAndProduto(Unidade unidade, Produto produto);
}
