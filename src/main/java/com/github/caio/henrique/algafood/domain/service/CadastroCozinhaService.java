package com.github.caio.henrique.algafood.domain.service;

import com.github.caio.henrique.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.github.caio.henrique.algafood.domain.exception.EntidadeEmUsoException;
import com.github.caio.henrique.algafood.domain.model.Cozinha;
import com.github.caio.henrique.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCozinhaService {

    private static final String MSG_COZINHA_NAO_ENCONTRADA = "Cozinha de código %d não pode ser removida, pois está em uso";
    public static final String MSG_COZINHA_EM_USO = "Não existe um cadastro de cozinha com código %d";

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {

        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void excluir(Long cozinhaId) {
        try {
            cozinhaRepository.deleteById(cozinhaId);
            cozinhaRepository.flush();
//            cozinhaRepository.saveAll();
        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException(cozinhaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO, cozinhaId));
        }
    }

    public Cozinha buscarOuFalhar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }
}
