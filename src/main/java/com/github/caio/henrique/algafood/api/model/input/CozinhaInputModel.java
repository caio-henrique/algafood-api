package com.github.caio.henrique.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class CozinhaInputModel {

    @ApiModelProperty(example = "Brasileira", required = true)
    @NotBlank
    private String nome;
}
