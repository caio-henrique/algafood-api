package com.github.caio.henrique.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class EstadoInputModel {

    @NotBlank
    private String nome;
}