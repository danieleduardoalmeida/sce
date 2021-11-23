package com.sce.api.tratamento.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.tratamento.exception.TratamentoApiException;
import com.sce.persistence.deposito.repository.DepositoRepository;
import com.sce.persistence.vistoria.repository.VistoriaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TratamentoDataValidator {

    @Autowired
    private VistoriaRepository vistoriaRepository;
    @Autowired
    private DepositoRepository depositoRepository;

    
    public void validarVistoria(long vistoriaId) throws TratamentoApiException {
        if (!vistoriaRepository.findById(vistoriaId).isPresent()) {
            log.warn("Vistoria não existe.");
            throw new TratamentoApiException("Vistoria não existe.", NOT_FOUND);
        }
    }
    
    public void validarDeposito(long depositoId) throws TratamentoApiException {
        if (!depositoRepository.findById(depositoId).isPresent()) {
            log.warn("Depósito não existe.");
            throw new TratamentoApiException("Depósito não existe.", NOT_FOUND);
        }
    }
}
