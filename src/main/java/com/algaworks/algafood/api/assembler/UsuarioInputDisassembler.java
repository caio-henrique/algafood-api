package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.UsuarioInputModel;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioInputModel usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInputModel usuarioInput, Usuario usuario) {
        modelMapper.map(usuarioInput, usuario);
    }
}
