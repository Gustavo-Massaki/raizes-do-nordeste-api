package com.yamamoto.raizesnordeste.controller;

import com.yamamoto.raizesnordeste.entity.Produto;
import com.yamamoto.raizesnordeste.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raizesnordeste/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public Produto createProduto(@RequestBody Produto produto){
        return produtoService.createProduto(produto);
    }

    @GetMapping
    public List<Produto> getAllProduto(){
        return produtoService.getAllProduto();
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable Long id){
        return produtoService.getProdutoById(id);
    }

    @DeleteMapping("/{id}")
    public void removeProduto(@PathVariable Long id){
        produtoService.removeProduto(id);
    }

    @PutMapping("/{id}")
    public Produto updateProduto(@PathVariable Long id, @RequestBody Produto updatedProduto){
        return produtoService.updateProduto(id, updatedProduto);
    }
}
