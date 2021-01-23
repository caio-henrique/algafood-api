package com.github.caio.henrique.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeInputModel {

    @ApiModelProperty(example = "Uberlândia", required = true)
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInputModel estado;
}
