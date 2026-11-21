package com.yamamoto.raizesnordeste.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class LogAuditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String acao;
    private String entidade;
    private Long entidadeId;
    private String usuarioEmail;
    private String detalhes;
    private LocalDateTime dataHora;

    public LogAuditoria(){}

    public LogAuditoria(String acao, String entidade, Long entidadeId, String usuarioEmail, String detalhes){
        this.acao = acao;
        this.entidade = entidade;
        this.entidadeId = entidadeId;
        this.usuarioEmail = usuarioEmail;
        this.detalhes = detalhes;
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getAcao() {
        return acao;
    }

    public String getEntidade() {
        return entidade;
    }

    public Long getEntidadeId() {
        return entidadeId;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
