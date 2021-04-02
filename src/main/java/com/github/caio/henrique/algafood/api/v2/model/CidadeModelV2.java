package com.github.caio.henrique.algafood.api.v2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@ApiModel("CidadeModel")
@Setter
@Getter
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2> {

    @ApiModelProperty(example = "1")
    private Long idCidade;

    @ApiModelProperty(example = "Uberlândia")
    private String nomeCidade;

    @ApiModelProperty(example = "1")
    private Long idEstado;

    @ApiModelProperty(example = "Minas Gerais")
    private String nomeEstado;
}
