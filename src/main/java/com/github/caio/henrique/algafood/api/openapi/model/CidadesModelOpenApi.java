package com.github.caio.henrique.algafood.api.openapi.model;

import com.github.caio.henrique.algafood.api.model.CidadeModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApi {

    private CidadeEmbeddedModelOpenApi _embedded;
    private Links _links;

    @ApiModel("CidadesEmbeddedModel")
    @Data
    private class CidadeEmbeddedModelOpenApi {

        private List<CidadeModel> cidades;
    }
}
