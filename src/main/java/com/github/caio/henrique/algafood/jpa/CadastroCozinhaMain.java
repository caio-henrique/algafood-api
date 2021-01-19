package com.github.caio.henrique.algafood.jpa;

import com.github.caio.henrique.algafood.AlgafoodApiApplication;
import com.github.caio.henrique.algafood.domain.model.Cozinha;
import com.github.caio.henrique.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class CadastroCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        cozinhaRepository.findAll().forEach(cozinha -> System.out.println(cozinha.getNome()));

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Brasileira");

        cozinhaRepository.save(cozinha1);
    }
}
