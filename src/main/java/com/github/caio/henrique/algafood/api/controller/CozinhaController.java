package com.github.caio.henrique.algafood.api.controller;

import com.github.caio.henrique.algafood.api.assembler.CozinhaInputDisassembler;
import com.github.caio.henrique.algafood.api.assembler.CozinhaModelAssembler;
import com.github.caio.henrique.algafood.api.model.CozinhaModel;
import com.github.caio.henrique.algafood.api.model.CozinhasXmlWrapper;
import com.github.caio.henrique.algafood.api.model.input.CozinhaInputModel;
import com.github.caio.henrique.algafood.domain.model.Cozinha;
import com.github.caio.henrique.algafood.domain.repository.CozinhaRepository;
import com.github.caio.henrique.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @GetMapping
    public Page<CozinhaModel> listar(@PageableDefault(size = 2) Pageable pageable) {
        Page<Cozinha> cozinhaPage = cozinhaRepository.findAll(pageable);
        List<CozinhaModel> cozinhaModels = cozinhaModelAssembler.toCollectionModel(cozinhaPage.getContent());
        Page<CozinhaModel> cozinhaModelPage = new PageImpl<>(cozinhaModels, pageable, cozinhaPage.getTotalElements());

        return cozinhaModelPage;
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.findAll());
    }

//    @GetMapping("/{cozinhaId}")
//    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
//
//        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
//
//        if(cozinha.isPresent())
//            return ResponseEntity.ok(cozinha.get());
//
//        return ResponseEntity.notFound().build();
//
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
////
////        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).build();
//    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInputModel cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        cozinha = cadastroCozinhaService.salvar(cozinha);

        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId,
                                  @RequestBody @Valid CozinhaInputModel cozinhaInput) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
        cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);

        return cozinhaModelAssembler.toModel(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("cozinhaId") Long id) {

        cadastroCozinhaService.excluir(id);
    }
}
