package com.yamamoto.raizesnordeste.service;

import com.yamamoto.raizesnordeste.entity.Unidade;
import com.yamamoto.raizesnordeste.exception.RecursoNaoEncontradoException;
import com.yamamoto.raizesnordeste.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;

    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public Unidade createUnidade(Unidade unidade){
        return unidadeRepository.save(unidade);
    }

    public List<Unidade> getAllUnidade(){
        return unidadeRepository.findAll();
    }

    public Unidade getUnidadeById(Long id){
        return unidadeRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Unidade nao encontrada"));
    }

    public void removeUnidade(Long id){
        unidadeRepository.deleteById(id);
    }

    public Unidade updateUnidade(Long id, Unidade updatedUnidade){
        Unidade unidade = unidadeRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Unidade nao encontrada"));
        unidade.setNome(updatedUnidade.getNome());
        unidade.setCidade(updatedUnidade.getCidade());

        return unidadeRepository.save(unidade);
    }

}
