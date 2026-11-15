package com.yamamoto.raizesnordeste.service;

import com.yamamoto.raizesnordeste.entity.Produto;
import com.yamamoto.raizesnordeste.exception.RecursoNaoEncontradoException;
import com.yamamoto.raizesnordeste.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto createProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> getAllProduto(){
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(Long id){
        return produtoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Produto nao encontrado"));
    }

    public void removeProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public Produto updateProduto(Long id, Produto updatedProduto){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Produto nao encontrado"));
        produto.setNome(updatedProduto.getNome());
        produto.setPreco(updatedProduto.getPreco());

        return produtoRepository.save(produto);
    }
}
