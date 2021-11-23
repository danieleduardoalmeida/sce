package com.sce.api.analise.especie.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.analise.especie.exception.AnaliseEspecieApiException;
import com.sce.persistence.analise.repository.AnaliseRepository;
import com.sce.persistence.especie.repository.EspecieRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnaliseEspecieDataValidator {

    @Autowired
    private AnaliseRepository analiseRepository;
    @Autowired
    private EspecieRepository especieRepository;

    public void validarAnalise(long analiseId) throws AnaliseEspecieApiException {
        if (!analiseRepository.findById(analiseId).isPresent()) {
            log.warn("Análise não existe.");
            throw new AnaliseEspecieApiException("Análise não existe.", NOT_FOUND);
        }
    }
    
    public void validarEspecie(long especieId) throws AnaliseEspecieApiException {
        if (!especieRepository.findById(especieId).isPresent()) {
            log.warn("Especie não existe.");
            throw new AnaliseEspecieApiException("Especie não existe.", NOT_FOUND);
        }
    }
    
}
