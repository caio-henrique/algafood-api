package com.github.caio.henrique.algafood.core.modelmapper;

import com.github.caio.henrique.algafood.api.model.EnderecoModel;
import com.github.caio.henrique.algafood.api.model.input.ItemPedidoInputModel;
import com.github.caio.henrique.algafood.domain.model.Endereco;
import com.github.caio.henrique.algafood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        var modelMapper = new ModelMapper();
        var endercoToEnderecoMotelTypeMap = modelMapper
                .createTypeMap(Endereco.class, EnderecoModel.class);
        endercoToEnderecoMotelTypeMap.<String>addMapping(src -> src.getCidade().getEstado().getNome(),
                (dest, value) -> dest.getCidade().setEstado(value));
        modelMapper.createTypeMap(ItemPedidoInputModel.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));
        return modelMapper;
    }
}
