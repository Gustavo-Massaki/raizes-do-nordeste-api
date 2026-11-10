package com.yamamoto.raizesnordeste.service;


import com.yamamoto.raizesnordeste.dto.PagamentoDTO;
import com.yamamoto.raizesnordeste.entity.Estoque;
import com.yamamoto.raizesnordeste.entity.ItemPedido;
import com.yamamoto.raizesnordeste.entity.Pedido;
import com.yamamoto.raizesnordeste.enums.ResultadoPagamento;
import com.yamamoto.raizesnordeste.enums.StatusPedido;
import com.yamamoto.raizesnordeste.repository.EstoqueRepository;
import com.yamamoto.raizesnordeste.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PagamentoService {

    private final PedidoRepository pedidoRepository;
    private final EstoqueRepository estoqueRepository;

    public PagamentoService(PedidoRepository pedidoRepository, EstoqueRepository estoqueRepository) {
        this.pedidoRepository = pedidoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @Transactional
    public void processarPagamento(PagamentoDTO pagamento){
        Pedido pedido = pedidoRepository.findById(pagamento.pedidoId()).orElseThrow(()-> new RuntimeException("Pedido não encontrado"));
        if (!pedido.getStatus().equals(StatusPedido.PENDENTE)){
            throw new RuntimeException("Pedido já processado");
        }
        if (pagamento.resultado() == ResultadoPagamento.APROVADO){

            for (ItemPedido item : pedido.getItens()) {
                Estoque estoque = estoqueRepository.findByUnidadeAndProduto(pedido.getUnidade(), item.getProduto()).orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
                if (estoque.getQuantidade() < item.getQuantidade()) {
                    throw new RuntimeException("Sem estoque suficiente para o pedido");
                }
            }
            for (ItemPedido item1 : pedido.getItens()){
                Estoque estoque1 = estoqueRepository.findByUnidadeAndProduto(pedido.getUnidade(), item1.getProduto()).orElseThrow(()-> new RuntimeException("Sem estoque suficiente para o pedido"));
                estoque1.setQuantidade(estoque1.getQuantidade() - item1.getQuantidade());
                estoqueRepository.save(estoque1);
            }
            pedido.setStatus(StatusPedido.PAGO);
            pedidoRepository.save(pedido);

        }
        else
        {
            pedido.setStatus(StatusPedido.PAGAMENTO_RECUSADO);
            pedidoRepository.save(pedido);
        }
    }
}
