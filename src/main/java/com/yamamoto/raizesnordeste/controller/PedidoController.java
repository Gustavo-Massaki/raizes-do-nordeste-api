package com.yamamoto.raizesnordeste.controller;


import com.yamamoto.raizesnordeste.dto.PedidoDTO;
import com.yamamoto.raizesnordeste.dto.PedidoResponseDTO;
import com.yamamoto.raizesnordeste.entity.Pedido;
import com.yamamoto.raizesnordeste.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raizesnordeste/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public PedidoResponseDTO createPedido(@RequestBody @Valid PedidoDTO pedido){
        return pedidoService.toResponseDTO(pedidoService.createPedido(pedido));
    }

    @GetMapping
    public List<PedidoResponseDTO> getAllPedido(){
        return pedidoService.getAllPedido().stream().map(pedido -> pedidoService.toResponseDTO(pedido)).toList();
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO getPedidoById(@PathVariable Long id){
        return pedidoService.toResponseDTO(pedidoService.getPedidoById(id));
    }
}
