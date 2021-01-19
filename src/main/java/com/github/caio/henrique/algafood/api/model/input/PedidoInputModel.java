package com.github.caio.henrique.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
public class PedidoInputModel {

    @Valid
    @NotNull
    private RestauranteIdInputModel restaurante;

    @Valid
    @NotNull
    private EnderecoInputModel enderecoEntrega;

    @Valid
    @NotNull
    private FormaPagamentoIdInputModel formaPagamento;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoInputModel> itens;
}
