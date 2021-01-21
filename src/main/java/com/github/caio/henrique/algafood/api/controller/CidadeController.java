package com.github.caio.henrique.algafood.api.controller;

import com.github.caio.henrique.algafood.api.assembler.CidadeInputDisassembler;
import com.github.caio.henrique.algafood.api.assembler.CidadeModelAssembler;
import com.github.caio.henrique.algafood.api.model.CidadeModel;
import com.github.caio.henrique.algafood.api.model.input.CidadeInputModel;
import com.github.caio.henrique.algafood.domain.exception.EstadoNaoEncontradoException;
import com.github.caio.henrique.algafood.domain.exception.NegocioException;
import com.github.caio.henrique.algafood.domain.model.Cidade;
import com.github.caio.henrique.algafood.domain.repository.CidadeRepository;
import com.github.caio.henrique.algafood.domain.service.CadastroCidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @GetMapping("/{cidadeId}")
    public CidadeModel buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);

        return cidadeModelAssembler.toModel(cidade);
    }

    @ApiOperation("Cadastra uma cidade")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel adicionar(@RequestBody @Valid CidadeInputModel cidadeInput) {
        try {
            Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

            cidade = cadastroCidade.salvar(cidade);

            return cidadeModelAssembler.toModel(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @ApiOperation("Alteração parcial de uma cidade")
    @PutMapping("/{cidadeId}")
    public CidadeModel atualizar(@PathVariable Long cidadeId,
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
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {

        cadastroCidade.excluir(cidadeId);
    }
}