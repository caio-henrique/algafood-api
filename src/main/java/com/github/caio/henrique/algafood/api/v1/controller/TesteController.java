package com.github.caio.henrique.algafood.api.v1.controller;

import com.github.caio.henrique.algafood.domain.model.Restaurante;
import com.github.caio.henrique.algafood.domain.repository.CozinhaRepository;
import com.github.caio.henrique.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

//    @GetMapping("/cozinhas/por-nome")
//    public List<Cozinha> cozinhasPorNome(String nome) {
//        return cozinhaRepository.findTodasByNomeContaining(nome);
//    }
//
//    @GetMapping("/cozinhas/unica-por-nome")
//    public Optional<Cozinha> cozinhaPorNome(String nome) {
//        return cozinhaRepository.findByNome(nome);
//    }

    @GetMapping("/cozinhas/exists")
    public boolean cozinhaExists(String nome) {
        return cozinhaRepository.existsByNome(nome);
    }

//    @GetMapping("/restaurantes/por-taxa-frete")
//    public List<Restaurante> restaurantesPorTaxaFrete(
//            BigDecimal taxaInicial, BigDecimal taxaFinal) {
//        return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
//    }
//
//    @GetMapping("/restaurantes/por-nome")
//    public List<Restaurante> restaurantesPorTaxaFrete(
//            String nome, Long cozinhaId) {
//        return restauranteRepository.consultarPorNome(nome, cozinhaId);
//    }
//
//    @GetMapping("/restaurantes/primeiro-por-nome")
//    public Optional<Restaurante> restaurantePrimeiroPorNome(String nome) {
//        return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
//    }

    @GetMapping("/restaurantes/top2-por-nome")
    public List<Restaurante> restaurantesTop2PorNome(String nome) {
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/por-nome-e-frete")
    public List<Restaurante> restaurantesPorNomeFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

        return restauranteRepository.find(nome, taxaInicial, taxaFinal);
    }

//    @GetMapping("/restaurantes/count-por-cozinha")
//    public int restaurantesCountPorCozinha(Long cozinhaId) {
//        return restauranteRepository.countByCozinhaId(cozinhaId);
//    }

    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome) {

        return restauranteRepository.findComFreteGratis(nome);
    }

    @GetMapping("/restaurantes/primeiro")
    public Optional<Restaurante> restaurantePrimeiro() {

        return restauranteRepository.buscarPrimeiro();
    }

}