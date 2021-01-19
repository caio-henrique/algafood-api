package com.github.caio.henrique.algafood.domain.repository;

import com.github.caio.henrique.algafood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

    FotoProduto save(FotoProduto foto);

    void delete(FotoProduto foto);
}
