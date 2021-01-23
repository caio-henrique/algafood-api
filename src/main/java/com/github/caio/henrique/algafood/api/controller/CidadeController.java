package com.github.caio.henrique.algafood.api.controller;

import com.github.caio.henrique.algafood.api.assembler.CidadeInputDisassembler;
import com.github.caio.henrique.algafood.api.assembler.CidadeModelAssembler;
import com.github.caio.henrique.algafood.api.exceptionhandler.Problem;
import com.github.caio.henrique.algafood.api.model.CidadeModel;
import com.github.caio.henrique.algafood.api.model.input.CidadeInputModel;
import com.github.caio.henrique.algafood.domain.exception.EstadoNaoEncontradoException;
import com.github.caio.henrique.algafood.domain.exception.NegocioException;
import com.github.caio.henrique.algafood.domain.model.Cidade;
import com.github.caio.henrique.algafood.domain.repository.CidadeRepository;
import com.github.caio.henrique.algafood.domain.service.CadastroCidadeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cidades")
@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @ApiOperation("Lista as cidades")
    @GetMapping
    public List<CidadeModel> listar() {
        List<Cidade> todasCidades = cidadeRepository.findAll();

        return cidadeModelAssembler.toCollectionModel(todasCidades);
    }

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@ApiParam(value = "ID de uma cidade", example = "1") @PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        return cidadeModelAssembler.toModel(cidade);
    }

    @ApiOperation("Cadastra uma cidade")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cidade cadastrada")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
            @RequestBody @Valid CidadeInputModel cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            cidade = cadastroCidade.salvar(cidade);

            return cidadeModelAssembler.toModel(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @ApiOperation("Alteração parcial de uma cidade")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cidade atualizada"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@ApiParam(value = "ID de uma cidade", example = "1") @PathVariable Long cidadeId,
            @ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
            @RequestBody @Valid CidadeInputModel cidadeInput) {
        try {
            Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

            cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

            cidadeAtual = cadastroCidade.salvar(cidadeAtual);

            return cidadeModelAssembler.toModel(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @ApiOperation("Exclui uma cidade")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cidade excluída"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@ApiParam(value = "ID de uma cidade", example = "1") @PathVariable Long cidadeId) {

        cadastroCidade.excluir(cidadeId);
    }
}