package com.yamamoto.raizesnordeste.service;

import com.yamamoto.raizesnordeste.entity.Estoque;
import com.yamamoto.raizesnordeste.entity.Produto;
import com.yamamoto.raizesnordeste.entity.Unidade;
import com.yamamoto.raizesnordeste.repository.EstoqueRepository;
import com.yamamoto.raizesnordeste.repository.ProdutoRepository;
import com.yamamoto.raizesnordeste.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    private final UnidadeRepository unidadeRepository;

    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository estoqueRepository, UnidadeRepository unidadeRepository, ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.unidadeRepository = unidadeRepository;
        this.produtoRepository = produtoRepository;
    }

    public Estoque createEstoque(Estoque estoque){

        Unidade unidade = unidadeRepository.findById(estoque.getUnidade().getId()).orElseThrow();
        Produto produto = produtoRepository.findById(estoque.getProduto().getId()).orElseThrow();

        if (estoqueRepository.existsByUnidadeAndProduto(unidade, produto)){
            throw new RuntimeException("Produto já existente na unidade");
        }

        estoque.setUnidade(unidade);
        estoque.setProduto(produto);

        return estoqueRepository.save(estoque);
    }

    public List<Estoque> getAllEstoque(){
        return estoqueRepository.findAll();
    }

    public Estoque getEstoqueById(Long id){
        return estoqueRepository.findById(id).orElseThrow();
    }

    public void removeEstoque(Long id){
        estoqueRepository.deleteById(id);
    }

    public Estoque updateEstoque(Long id, Estoque updatedEstoque){
        Estoque estoque = estoqueRepository.findById(id).orElseThrow();
        Unidade unidade = estoque.getUnidade();
        Produto produto = produtoRepository.findById(updatedEstoque.getProduto().getId()).orElseThrow();

        if (estoqueRepository.existsByUnidadeAndProdutoAndIdNot(unidade, produto, id)) {
            throw new RuntimeException("Produto já cadastrado no estoque desta unidade");
        }

        estoque.setProduto(produto);
        estoque.setQuantidade(updatedEstoque.getQuantidade());
        return estoqueRepository.save(estoque);
    }
}
