package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.GrupoInputModel;
import com.algaworks.algafood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Grupo toDomainObject(GrupoInputModel grupoInput) {
        return modelMapper.map(grupoInput, Grupo.class);
    }

    public void copyToDomainObject(GrupoInputModel grupoInput, Grupo grupo) {
        modelMapper.map(grupoInput, grupo);
    }
}
