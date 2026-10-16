package com.yamamoto.raizesnordeste.controller;

import com.yamamoto.raizesnordeste.entity.Unidade;
import com.yamamoto.raizesnordeste.service.UnidadeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raizesnordeste/unidade")
public class UnidadeController {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @PostMapping
    public Unidade createUnidade(@RequestBody Unidade unidade){
        return unidadeService.createUnidade(unidade);
    }

    @GetMapping
    public List<Unidade> getAllUnidade(){
        return unidadeService.getAllUnidade();
    }

    @GetMapping("/{id}")
    public Unidade getUnidadeById(@PathVariable Long id){
        return unidadeService.getUnidadeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUnidade(@PathVariable Long id){
        unidadeService.removeUnidade(id);
    }

    @PutMapping("/{id}")
    public Unidade updateUnidade(@PathVariable Long id, @RequestBody Unidade updatedUnidade){
        return unidadeService.updateUnidade(id, updatedUnidade);
    }
}
