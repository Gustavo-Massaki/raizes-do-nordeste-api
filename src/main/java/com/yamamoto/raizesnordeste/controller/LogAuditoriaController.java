package com.yamamoto.raizesnordeste.controller;

import com.yamamoto.raizesnordeste.entity.LogAuditoria;
import com.yamamoto.raizesnordeste.service.LogAuditoriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/raizesnordeste/logs")
public class LogAuditoriaController {
    private final LogAuditoriaService logAuditoriaService;

    public LogAuditoriaController(LogAuditoriaService logAuditoriaService) {
        this.logAuditoriaService = logAuditoriaService;
    }

    @GetMapping
    public List<LogAuditoria> getAllLogAuditoria(){
        return logAuditoriaService.getAllLogAuditoria();
    }
}
