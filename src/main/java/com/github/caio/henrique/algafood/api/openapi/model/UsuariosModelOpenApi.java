package com.github.caio.henrique.algafood.api.openapi.model;

import com.github.caio.henrique.algafood.api.model.UsuarioModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("UsuariosModel")
@Data
public class UsuariosModelOpenApi {

    private UsuariosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("UsuariosEmbeddedModel")
    @Data
    public class UsuariosEmbeddedModelOpenApi {

        private List<UsuarioModel> usuarios;
    }
}
