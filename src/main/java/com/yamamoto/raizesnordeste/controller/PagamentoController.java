package com.yamamoto.raizesnordeste.controller;

import com.yamamoto.raizesnordeste.dto.PagamentoDTO;
import com.yamamoto.raizesnordeste.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raizesnordeste/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public void processarPagamento(@RequestBody @Valid PagamentoDTO pagamento){
        pagamentoService.processarPagamento(pagamento);
    }
}
