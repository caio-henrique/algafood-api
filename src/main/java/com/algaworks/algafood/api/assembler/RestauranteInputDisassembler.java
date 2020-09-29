package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.RestauranteInputModel;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
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
        modelMapper.map(restauranteInputModel, restaurante);
    }
}
