package com.yamamoto.raizesnordeste.service;

import com.yamamoto.raizesnordeste.dto.ItemPedidoDTO;
import com.yamamoto.raizesnordeste.dto.ItemPedidoResponseDTO;
import com.yamamoto.raizesnordeste.dto.PedidoDTO;
import com.yamamoto.raizesnordeste.dto.PedidoResponseDTO;
import com.yamamoto.raizesnordeste.entity.*;
import com.yamamoto.raizesnordeste.enums.StatusPedido;
import com.yamamoto.raizesnordeste.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UnidadeRepository unidadeRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, UnidadeRepository unidadeRepository, ProdutoRepository produtoRepository, EstoqueRepository estoqueRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.unidadeRepository = unidadeRepository;
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
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
        novoPedido.definirDataHora();

        BigDecimal valorTotal = BigDecimal.ZERO;

        Map<Long, Integer> quantidades = new HashMap<>();

        for (ItemPedidoDTO item : pedido.itens()){
            quantidades.merge(item.produtoId(), item.quantidade(), Integer::sum);
        }

        for (Map.Entry<Long, Integer> entrada : quantidades.entrySet()) {
            Produto produto = produtoRepository.findById(entrada.getKey()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            Estoque estoque = estoqueRepository.findByUnidadeAndProduto(unidade, produto).orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

            if (estoque.getQuantidade() < entrada.getValue()){
                throw new RuntimeException("Não foi possivel realizar pedido");
            }
        }

        for (ItemPedidoDTO item : pedido.itens()){
            Produto produto = produtoRepository.findById(item.produtoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            novoPedido.adicionarItem(new ItemPedido(item.quantidade(), produto.getPreco(),novoPedido, produto));
            valorTotal = valorTotal.add(produto.getPreco().multiply(BigDecimal.valueOf(item.quantidade())));
        }

        novoPedido.definirValorTotal(valorTotal);
        return pedidoRepository.save(novoPedido);
    }

    public List<Pedido> getAllPedido(){
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long id){
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public PedidoResponseDTO toResponseDTO(Pedido pedido){
        List<ItemPedidoResponseDTO> itens = pedido.getItens().stream().map(item ->
                new ItemPedidoResponseDTO(
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getValorUnitario())).toList();
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getUsuario().getNome(),
                pedido.getUnidade().getNome(),
                pedido.getStatus(),
                pedido.getFormaPagamento(),
                pedido.getCanalPedido(),
                pedido.getValorTotal(),
                pedido.getDataHora(),
                itens
        );
    }
}
