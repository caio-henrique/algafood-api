package com.github.caio.henrique.algafood.domain.service;

import com.github.caio.henrique.algafood.domain.model.dto.VendaDiaria;
import com.github.caio.henrique.algafood.domain.filter.VendaDiariaFilter;

import java.util.List;

public interface VendasQueryService {

    List<VendaDiaria> consultaVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
