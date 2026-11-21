package com.yamamoto.raizesnordeste.service;

import com.yamamoto.raizesnordeste.entity.LogAuditoria;
import com.yamamoto.raizesnordeste.repository.LogAuditoriaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogAuditoriaService {

    private final LogAuditoriaRepository logAuditoriaRepository;

    public LogAuditoriaService(LogAuditoriaRepository logAuditoriaRepository) {
        this.logAuditoriaRepository = logAuditoriaRepository;
    }

    public void registrar(String acao, String entidade, Long entidadeId, String detalhes){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = (auth != null && auth.isAuthenticated()) ? auth.getName() : "SISTEMA";
        logAuditoriaRepository.save(new LogAuditoria(acao, entidade, entidadeId, email, detalhes));
    }

    public List<LogAuditoria> getAllLogAuditoria(){
        return logAuditoriaRepository.findAll();
    }
}
