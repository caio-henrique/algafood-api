package com.github.caio.henrique.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Setter
@Getter
public class RestauranteInputModel {

    @ApiModelProperty(example = "Thai Gourmet", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "12.00", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    private CozinhaIdInputModel cozinha;

    @Valid
    @NotNull
    private EnderecoInputModel endereco;
}
