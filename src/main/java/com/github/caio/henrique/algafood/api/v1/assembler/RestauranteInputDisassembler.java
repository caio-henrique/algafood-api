package com.github.caio.henrique.algafood.api.v1.assembler;

import com.github.caio.henrique.algafood.api.v1.model.input.RestauranteInputModel;
import com.github.caio.henrique.algafood.domain.model.Cidade;
import com.github.caio.henrique.algafood.domain.model.Cozinha;
import com.github.caio.henrique.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInputModel restauranteInputModel) {

        return modelMapper.map(restauranteInputModel, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInputModel restauranteInputModel, Restaurante restaurante) {

        restaurante.setCozinha(new Cozinha());

        if(restaurante.getEndereco() != null)
            restaurante.getEndereco().setCidade(new Cidade());

        modelMapper.map(restauranteInputModel, restaurante);
    }
}
