package com.sce.api.exemplar.analise.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.exemplar.analise.exception.ExemplarAnaliseApiException;
import com.sce.persistence.analise.repository.AnaliseRepository;
import com.sce.persistence.especie.repository.EspecieRepository;
import com.sce.persistence.exemplar.repository.ExemplarRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExemplarAnaliseDataValidator {

    @Autowired
    private ExemplarRepository exemplarRepository;
    @Autowired
    private AnaliseRepository analiseRepository;
    @Autowired
    private EspecieRepository especieRepository;

    public void validarExemplar(long exemplarId) throws ExemplarAnaliseApiException {
        if (!exemplarRepository.findById(exemplarId).isPresent()) {
            log.warn("Exemplar não existe.");
            throw new ExemplarAnaliseApiException("Exemplar não existe.", NOT_FOUND);
        }
    }
    
    public void validarEspecie(long especieId) throws ExemplarAnaliseApiException {
        if (!especieRepository.findById(especieId).isPresent()) {
            log.warn("Espécie não existe.");
            throw new ExemplarAnaliseApiException("Espécie não existe.", NOT_FOUND);
        }
    }

    public void validarAnalise(long analiseId) throws ExemplarAnaliseApiException {
        if (!analiseRepository.findById(analiseId).isPresent()) {
            log.warn("Análise não existe.");
            throw new ExemplarAnaliseApiException("Análise não existe.", NOT_FOUND);
        }
    }

}
