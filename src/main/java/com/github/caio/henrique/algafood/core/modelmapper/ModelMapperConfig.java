package com.github.caio.henrique.algafood.core.modelmapper;

import com.github.caio.henrique.algafood.api.v1.model.EnderecoModel;
import com.github.caio.henrique.algafood.api.v1.model.input.ItemPedidoInputModel;
import com.github.caio.henrique.algafood.api.v2.model.imput.CidadeInputModelV2;
import com.github.caio.henrique.algafood.domain.model.Cidade;
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
        modelMapper.createTypeMap(CidadeInputModelV2.class, Cidade.class)
                .addMappings(mapper -> mapper.skip(Cidade::setId));
        modelMapper.createTypeMap(ItemPedidoInputModel.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
                Endereco.class, EnderecoModel.class);
        enderecoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));

        return modelMapper;
    }
}
