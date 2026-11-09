package com.yamamoto.raizesnordeste.service;

import com.yamamoto.raizesnordeste.dto.ItemPedidoDTO;
import com.yamamoto.raizesnordeste.dto.PedidoDTO;
import com.yamamoto.raizesnordeste.entity.*;
import com.yamamoto.raizesnordeste.enums.StatusPedido;
import com.yamamoto.raizesnordeste.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UnidadeRepository unidadeRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, UnidadeRepository unidadeRepository, ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository, ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.unidadeRepository = unidadeRepository;
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Transactional
    public Pedido createPedido(PedidoDTO pedido){
        Usuario usuario = usuarioRepository.findById(pedido.usuarioId()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        Unidade unidade = unidadeRepository.findById(pedido.unidadeId()).orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        Pedido novoPedido = new Pedido();

        novoPedido.setUsuario(usuario);
        novoPedido.setUnidade(unidade);
        novoPedido.setStatus(StatusPedido.PENDENTE);
        novoPedido.setFormaPagamento(pedido.formaPagamento());
        novoPedido.setCanalPedido(pedido.canalPedido());

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedidoDTO item : pedido.itens()) {
            Produto produto = produtoRepository.findById(item.produtoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            Estoque estoque = estoqueRepository.findByUnidadeAndProduto(unidade, produto).orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

            if (estoque.getQuantidade() < item.quantidade()){
                throw new RuntimeException("Não foi possivel realizar pedido");
            }

            ItemPedido itemPedido = new ItemPedido(item.quantidade(), produto.getPreco(),novoPedido, produto);
            novoPedido.adicionarItem(itemPedido);
            valorTotal = valorTotal.add(produto.getPreco().multiply(BigDecimal.valueOf(item.quantidade())));
        }
        novoPedido.definirValorTotal(valorTotal);
        return pedidoRepository.save(novoPedido);
    }



}
