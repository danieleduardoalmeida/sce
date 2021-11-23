package com.sce.api.deposito.vistoria.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.deposito.vistoria.exception.DepositoVistoriaApiException;
import com.sce.persistence.deposito.repository.DepositoRepository;
import com.sce.persistence.vistoria.repository.VistoriaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepositoVistoriaDataValidator {
    
    @Autowired
    private VistoriaRepository vistoriaRepository;
    @Autowired
    private DepositoRepository depositoRepository;
    
    public void validarVistoria(long vistoriaId) throws DepositoVistoriaApiException {
        if (!vistoriaRepository.findById(vistoriaId).isPresent()) {
            log.warn("Vistoria não existe.");
            throw new DepositoVistoriaApiException("Vistoria não existe.", NOT_FOUND);
        }
    }
    
    public void validarDeposito(long depositoId) throws DepositoVistoriaApiException {
        if (!depositoRepository.findById(depositoId).isPresent()) {
            log.warn("Depósito não existe.");
            throw new DepositoVistoriaApiException("Depósito não existe.", NOT_FOUND);
        }
    }

}
