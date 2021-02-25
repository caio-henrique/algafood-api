package com.github.caio.henrique.algafood.api.assembler;

import com.github.caio.henrique.algafood.api.controller.UsuarioController;
import com.github.caio.henrique.algafood.api.controller.UsuarioGrupoController;
import com.github.caio.henrique.algafood.api.model.UsuarioModel;
import com.github.caio.henrique.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModelAssembler() {
        super(UsuarioController.class, UsuarioModel.class);
    }
//
//
//    public UsuarioModel toModel(Usuario usuario) {
//        return modelMapper.map(usuario, UsuarioModel.class);
//    }
//
//    public List<UsuarioModel> toCollectionModel(Collection<Usuario> usuarios) {
//        return usuarios.stream()
//                .map(usuario -> toModel(usuario))
//                .collect(Collectors.toList());
//    }

    @Override
    public UsuarioModel toModel(Usuario usuario) {
        UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
        modelMapper.map(usuario, usuarioModel);

        usuarioModel.add(linkTo(UsuarioController.class).withRel("usuarios"));

        usuarioModel.add(linkTo(methodOn(UsuarioGrupoController.class)
                .listar(usuario.getId())).withRel("grupos-usuario"));

        return usuarioModel;
    }

    @Override
    public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(UsuarioController.class).withSelfRel());
    }
}
