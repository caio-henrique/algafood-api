package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.PedidoInputModel;
import com.algaworks.algafood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Pedido toDomainObject(PedidoInputModel pedidoInput) {
        return modelMapper.map(pedidoInput, Pedido.class);
    }

    public void copyToDomainObject(PedidoInputModel pedidoInput, Pedido pedido) {
        modelMapper.map(pedidoInput, pedido);
    }
}
