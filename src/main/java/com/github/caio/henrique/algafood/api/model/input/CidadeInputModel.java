package com.github.caio.henrique.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeInputModel {

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInputModel estado;
}
