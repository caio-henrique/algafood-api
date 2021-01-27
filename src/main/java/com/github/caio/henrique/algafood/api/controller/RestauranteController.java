package com.github.caio.henrique.algafood.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.caio.henrique.algafood.api.assembler.RestauranteInputDisassembler;
import com.github.caio.henrique.algafood.api.assembler.RestauranteModelAssembler;
import com.github.caio.henrique.algafood.api.model.RestauranteModel;
import com.github.caio.henrique.algafood.api.model.input.CozinhaIdInputModel;
import com.github.caio.henrique.algafood.api.model.input.RestauranteInputModel;
import com.github.caio.henrique.algafood.api.model.view.RestauranteView;
import com.github.caio.henrique.algafood.api.openapi.controller.RestauranteControllerOpenApi;
import com.github.caio.henrique.algafood.domain.exception.CidadeNaoEncontradaException;
import com.github.caio.henrique.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.github.caio.henrique.algafood.domain.exception.NegocioException;
import com.github.caio.henrique.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.github.caio.henrique.algafood.domain.model.Restaurante;
import com.github.caio.henrique.algafood.domain.repository.RestauranteRepository;
import com.github.caio.henrique.algafood.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;


    @JsonView(RestauranteView.Resumo.class)
    @GetMapping
    public List<RestauranteModel> listar() {
        return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=apenas-nome")
    public List<RestauranteModel> listarApenasNomes() {
        return listar();
    }

//    @GetMapping
//	public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {
//		List<Restaurante> restaurantes = restauranteRepository.findAll();
//		List<RestauranteModel> restaurantesModel = restauranteModelAssembler.toCollectionModel(restaurantes);
//
//		MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantesModel);
//		restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//
//		if ("nome".equals(projecao)) {
//			restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//		} else if ("completo".equals(projecao)) {
//			restaurantesWrapper.setSerializationView(null);
//		}
//
//		return restaurantesWrapper;
//	}

//    @GetMapping
//    public ResponseEntity<List<RestauranteModel>> listar() {
//
//        List<Restaurante> restaurantes = restauranteRepository.findAll();
//        List<RestauranteModel> restauranteModels = restauranteModelAssembler.toCollectionModel(restaurantes);
//
//        return ResponseEntity.ok()
//                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
//                .body(restauranteModels);
//    }
//
//    @GetMapping(params = "projecao=resumo")
//    @JsonView(RestauranteView.Resumo.class)
//    public ResponseEntity<List<RestauranteModel>> listarResumido() {
//
//        List<Restaurante> restaurantes = restauranteRepository.findAll();
//        List<RestauranteModel> restauranteModels = restauranteModelAssembler.toCollectionModel(restaurantes);
//
//        return ResponseEntity.ok()
//                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
//                .body(restauranteModels);
//    }
//
//    @GetMapping(params = "projecao=nome")
//    @JsonView(RestauranteView.ApenasNome.class)
//    public ResponseEntity<List<RestauranteModel>> listarNomes() {
//
//        List<Restaurante> restaurantes = restauranteRepository.findAll();
//        List<RestauranteModel> restauranteModels = restauranteModelAssembler.toCollectionModel(restaurantes);
//
//        return ResponseEntity.ok()
//                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
//                .body(restauranteModels);
//    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
        return restauranteModelAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInputModel restauranteInputModel) {
        try {

            Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInputModel);
            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
                                      @RequestBody @Valid RestauranteInputModel restauranteInputModel) {

        try {

            Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
            restauranteInputDisassembler.copyToDomainObject(restauranteInputModel, restauranteAtual);
            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        cadastroRestaurante.ativar(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {

        try {

            cadastroRestaurante.ativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException exception) {
            throw new NegocioException(exception.getMessage(), exception);
        }
    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {

        try {

            cadastroRestaurante.inativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException exception) {
            throw new NegocioException(exception.getMessage(), exception);
        }
    }

    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
        cadastroRestaurante.inativar(restauranteId);
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {
        cadastroRestaurante.abrir(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId) {
        cadastroRestaurante.fechar(restauranteId);
    }

    @PatchMapping("/{restauranteId}")
    public RestauranteModel atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos,
                                        HttpServletRequest request) {

        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);
        this.merge(campos, restauranteAtual, request);

        return atualizar(restauranteId, this.toInputModel(restauranteAtual));
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException exception) {

            ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
            Throwable rootCause = ExceptionUtils.getRootCause(exception);
            throw new HttpMessageNotReadableException(exception.getMessage(), rootCause, serverHttpRequest);
        }
    }

    private RestauranteInputModel toInputModel(Restaurante restaurante) {

        CozinhaIdInputModel cozinhaIdInputModel = new CozinhaIdInputModel();
        cozinhaIdInputModel.setId(restaurante.getCozinha().getId());

        RestauranteInputModel restauranteInputModel = new RestauranteInputModel();
        restauranteInputModel.setCozinha(cozinhaIdInputModel);
        restauranteInputModel.setNome(restaurante.getNome());
        restauranteInputModel.setTaxaFrete(restaurante.getTaxaFrete());
        return restauranteInputModel;
    }
}
