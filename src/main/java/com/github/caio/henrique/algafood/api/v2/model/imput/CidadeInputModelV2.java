package com.github.caio.henrique.algafood.api.v2.model.imput;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("CidadeInputModel")
@Setter
@Getter
public class CidadeInputModelV2 {

    @ApiModelProperty(example = "Uberlândia", required = true)
    @NotBlank
    private String nomeCidadede;

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long idEstado;
}
