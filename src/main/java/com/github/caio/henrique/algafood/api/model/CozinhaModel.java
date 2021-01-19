package com.github.caio.henrique.algafood.api.model;

import com.github.caio.henrique.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaModel {

    private Long id;
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
