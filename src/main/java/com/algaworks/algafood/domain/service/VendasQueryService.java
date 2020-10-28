package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;

import java.util.List;

public interface VendasQueryService {

    List<VendaDiaria> consultaVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
