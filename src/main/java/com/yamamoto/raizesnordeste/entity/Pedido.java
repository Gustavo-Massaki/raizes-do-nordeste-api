package com.yamamoto.raizesnordeste.entity;

import com.yamamoto.raizesnordeste.enums.CanalPedido;
import com.yamamoto.raizesnordeste.enums.FormaPagamento;
import com.yamamoto.raizesnordeste.enums.StatusPedido;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Unidade unidade;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    private CanalPedido canalPedido;

    private BigDecimal valorTotal;
    private LocalDateTime dataHora;

    public void adicionarItem(ItemPedido item){
        itens.add(item);
        item.setPedido(this);
    }

    public void definirValorTotal(BigDecimal valorTotal){
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public CanalPedido getCanalPedido() {
        return canalPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setCanalPedido(CanalPedido canalPedido) {
        this.canalPedido = canalPedido;
    }
}
