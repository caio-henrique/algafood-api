package com.github.caio.henrique.algafood.domain.service;

import com.github.caio.henrique.algafood.domain.filter.VendaDiariaFilter;

public interface VendasReportService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
