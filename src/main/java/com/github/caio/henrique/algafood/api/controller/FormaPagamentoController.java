package com.github.caio.henrique.algafood.api.controller;

import com.github.caio.henrique.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.github.caio.henrique.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.github.caio.henrique.algafood.api.model.FormaPagamentoModel;
import com.github.caio.henrique.algafood.api.model.input.FormaPagamentoInputModel;
import com.github.caio.henrique.algafood.api.openapi.controller.FormaPagamentoControllerOpenApi;
import com.github.caio.henrique.algafood.domain.model.FormaPagamento;
import com.github.caio.henrique.algafood.domain.repository.FormaPagamentoRepository;
import com.github.caio.henrique.algafood.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @GetMapping
    public List<FormaPagamentoModel> listar() {
        List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();

        return formaPagamentoModelAssembler.toCollectionModel(todasFormasPagamentos);
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

        return formaPagamentoModelAssembler.toModel(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInputModel formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);

        formaPagamento = cadastroFormaPagamento.salvar(formaPagamento);

        return formaPagamentoModelAssembler.toModel(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
                                         @RequestBody @Valid FormaPagamentoInputModel formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        formaPagamentoAtual = cadastroFormaPagamento.salvar(formaPagamentoAtual);

        return formaPagamentoModelAssembler.toModel(formaPagamentoAtual);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamento.excluir(formaPagamentoId);
    }
}
