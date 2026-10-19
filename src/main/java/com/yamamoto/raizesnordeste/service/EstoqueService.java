package com.yamamoto.raizesnordeste.service;

import com.yamamoto.raizesnordeste.entity.Estoque;
import com.yamamoto.raizesnordeste.repository.EstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public Estoque createEstoque(Estoque estoque){

        if (estoqueRepository.existsByUnidadeAndProduto(estoque.getUnidade(), estoque.getProduto())){
            throw new RuntimeException("Produto já existente na unidade");
        }
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

        estoque.setProduto(updatedEstoque.getProduto());
        estoque.setQuantidade(updatedEstoque.getQuantidade());

        return estoqueRepository.save(estoque);
    }
}
