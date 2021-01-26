package com.github.caio.henrique.algafood.api.model;

import com.github.caio.henrique.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Brasileira")
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
