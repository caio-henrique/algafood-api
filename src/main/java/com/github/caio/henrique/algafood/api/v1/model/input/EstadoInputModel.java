package com.github.caio.henrique.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class EstadoInputModel {

    @ApiModelProperty(example = "Minas Gerais", required = true)
    @NotBlank
    private String nome;
}
