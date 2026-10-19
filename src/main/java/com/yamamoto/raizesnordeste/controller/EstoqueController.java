package com.yamamoto.raizesnordeste.controller;

import com.yamamoto.raizesnordeste.entity.Estoque;
import com.yamamoto.raizesnordeste.service.EstoqueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raizesnordeste/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public Estoque createEstoque(@RequestBody Estoque estoque){
        return estoqueService.createEstoque(estoque);
    }

    @GetMapping
    public List<Estoque> getAllEstoque(){
        return estoqueService.getAllEstoque();
    }

    @GetMapping("/{id}")
    public Estoque getEstoqueById(@PathVariable Long id){
        return estoqueService.getEstoqueById(id);
    }

    @DeleteMapping("/{id}")
    public void removeEstoque(@PathVariable Long id){
        estoqueService.removeEstoque(id);
    }

    @PutMapping("/{id}")
    public Estoque updateEstoque(@PathVariable Long id, @RequestBody Estoque updatedEstoque){
        return estoqueService.updateEstoque(id, updatedEstoque);
    }

}
