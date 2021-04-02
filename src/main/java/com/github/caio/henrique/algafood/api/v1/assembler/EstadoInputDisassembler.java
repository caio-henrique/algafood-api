package com.github.caio.henrique.algafood.api.v1.assembler;

import com.github.caio.henrique.algafood.api.v1.model.input.EstadoInputModel;
import com.github.caio.henrique.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Estado toDomainObject(EstadoInputModel estadoInput) {
        return modelMapper.map(estadoInput, Estado.class);
    }

    public void copyToDomainObject(EstadoInputModel estadoInput, Estado estado) {
        modelMapper.map(estadoInput, estado);
    }
}
