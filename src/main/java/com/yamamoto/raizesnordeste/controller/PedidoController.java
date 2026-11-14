package com.yamamoto.raizesnordeste.controller;

import com.yamamoto.raizesnordeste.dto.PedidoDTO;
import com.yamamoto.raizesnordeste.dto.PedidoResponseDTO;
import com.yamamoto.raizesnordeste.entity.Pedido;
import com.yamamoto.raizesnordeste.enums.CanalPedido;
import com.yamamoto.raizesnordeste.enums.StatusPedido;
import com.yamamoto.raizesnordeste.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDTO createPedido(@RequestBody @Valid PedidoDTO pedido){
        return pedidoService.toResponseDTO(pedidoService.createPedido(pedido));
    }

    @GetMapping
    public List<PedidoResponseDTO> getAllPedido(@RequestParam(required = false)CanalPedido canalPedido, @RequestParam(required = false) StatusPedido status){
        List<Pedido> pedidos;
        if (canalPedido != null && status != null){
            pedidos = pedidoService.findByCanalPedidoAndStatus(canalPedido, status);
        }
        else if (canalPedido != null){
            pedidos = pedidoService.findByCanalPedido(canalPedido);
        }
        else if (status != null){
            pedidos = pedidoService.findByStatus(status);
        }
        else {
            pedidos = pedidoService.getAllPedido();
        }
        return pedidos.stream().map(pedido -> pedidoService.toResponseDTO(pedido)).toList();
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO getPedidoById(@PathVariable Long id){
        return pedidoService.toResponseDTO(pedidoService.getPedidoById(id));
    }

    @PutMapping("/{id}/status")
    public PedidoResponseDTO atualizarStatus(@PathVariable Long id, @RequestParam StatusPedido novoStatus){
        return pedidoService.toResponseDTO(pedidoService.atualizarStatus(id, novoStatus));
    }
}
