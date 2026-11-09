package com.yamamoto.raizesnordeste.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;
    private BigDecimal valorUnitario;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

    protected ItemPedido(){
    }

    public ItemPedido(Integer quantidade, BigDecimal valorUnitario, Pedido pedido, Produto produto){
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.pedido = pedido;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
