package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.EstadoInputModel;
import com.algaworks.algafood.domain.model.Estado;
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
