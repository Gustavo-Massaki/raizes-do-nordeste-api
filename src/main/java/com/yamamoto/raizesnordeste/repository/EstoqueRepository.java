package com.yamamoto.raizesnordeste.repository;

import com.yamamoto.raizesnordeste.entity.Estoque;
import com.yamamoto.raizesnordeste.entity.Produto;
import com.yamamoto.raizesnordeste.entity.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    boolean existsByUnidadeAndProduto(Unidade unidade, Produto produto);

}
