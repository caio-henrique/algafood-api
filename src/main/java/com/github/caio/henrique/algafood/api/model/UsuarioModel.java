package com.github.caio.henrique.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Joel da Silva")
    private String nome;

    @ApiModelProperty(example = "joel.alg@algafood.com.br")
    private String email;
}
