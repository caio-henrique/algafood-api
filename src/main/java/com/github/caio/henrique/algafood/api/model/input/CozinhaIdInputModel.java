package com.github.caio.henrique.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CozinhaIdInputModel {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;
}
