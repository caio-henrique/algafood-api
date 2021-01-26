package com.github.caio.henrique.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class GrupoInputModel {

    @ApiModelProperty(example = "Gerente", required = true)
    @NotBlank
    private String nome;
}
