package com.github.caio.henrique.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeIdInputModel {

    @NotNull
    private Long id;
}