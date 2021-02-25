package com.github.caio.henrique.algafood.api.controller;

import com.github.caio.henrique.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.github.caio.henrique.algafood.api.model.FormaPagamentoModel;
import com.github.caio.henrique.algafood.api.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.github.caio.henrique.algafood.domain.model.Restaurante;
import com.github.caio.henrique.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @GetMapping
    public List<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {

        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        return formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormaPagamentos());
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {

        cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);
    }

    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {

        cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);
    }
}
