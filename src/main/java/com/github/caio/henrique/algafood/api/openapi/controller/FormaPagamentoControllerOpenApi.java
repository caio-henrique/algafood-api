package com.github.caio.henrique.algafood.api.openapi.controller;

import com.github.caio.henrique.algafood.api.exceptionhandler.Problem;
import com.github.caio.henrique.algafood.api.model.FormaPagamentoModel;
import com.github.caio.henrique.algafood.api.model.input.FormaPagamentoInputModel;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

    @ApiOperation("Lista as formas de pagamento")
    List<FormaPagamentoModel> listar();

    @ApiOperation("Busca uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    FormaPagamentoModel buscar(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1")
                    Long formaPagamentoId);

    @ApiOperation("Cadastra uma forma de pagamento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Forma de pagamento cadastrada"),
    })
    FormaPagamentoModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento")
                    FormaPagamentoInputModel formaPagamentoInput);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Forma de pagamento atualizada"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    FormaPagamentoModel atualizar(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1")
                    Long formaPagamentoId,

            @ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados")
                    FormaPagamentoInputModel formaPagamentoInput);

    @ApiOperation("Exclui uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Forma de pagamento excluída"),
            @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
    })
    void remover(Long formaPagamentoId);
}
