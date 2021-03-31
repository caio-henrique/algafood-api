package com.github.caio.henrique.algafood.api.openapi.model;

import com.github.caio.henrique.algafood.api.model.CidadeModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CozinhasModel")
@Setter
@Getter
public class CozinhasModelOpenApi {

    private CozinhasEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @ApiModel("CozinhasEmbeddedModel")
    @Data
    private class CozinhasEmbeddedModelOpenApi {

        private List<CidadeModel> cozinhas;
    }
}