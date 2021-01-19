package com.github.caio.henrique.algafood.api.assembler;

import com.github.caio.henrique.algafood.api.model.input.ProdutoInputModel;
import com.github.caio.henrique.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoInputModel produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainObject(ProdutoInputModel produtoInput, Produto produto) {
        modelMapper.map(produtoInput, produto);
    }
}
