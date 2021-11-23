package com.sce.api.amostra.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.amostra.exception.AmostraApiException;
import com.sce.persistence.analise.repository.AnaliseRepository;
import com.sce.persistence.deposito.repository.DepositoRepository;
import com.sce.persistence.vistoria.repository.VistoriaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AmostraDataValidator {

    @Autowired
    private AnaliseRepository analiseRepository;
    @Autowired
    private VistoriaRepository vistoriaRepository;
    @Autowired
    private DepositoRepository depositoRepository;

    public void validarAnalise(long analiseId) throws AmostraApiException {
        if (!analiseRepository.findById(analiseId).isPresent()) {
            log.warn("Análise não existe.");
            throw new AmostraApiException("Análise não existe.", NOT_FOUND);
        }
    }
    
    public void validarVistoria(long vistoriaId) throws AmostraApiException {
        if (!vistoriaRepository.findById(vistoriaId).isPresent()) {
            log.warn("Vistoria não existe.");
            throw new AmostraApiException("Vistoria não existe.", NOT_FOUND);
        }
    }
    
    public void validarDeposito(long depositoId) throws AmostraApiException {
        if (!depositoRepository.findById(depositoId).isPresent()) {
            log.warn("Depósito não existe.");
            throw new AmostraApiException("Depósito não existe.", NOT_FOUND);
        }
    }
}
