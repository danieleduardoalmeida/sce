package com.sce.api.analise.service;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sce.api.analise.model.AnaliseVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnaliseRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AnaliseVo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.debug("Validating the Analise request.");
        AnaliseVo request = (AnaliseVo) target;

        if (validarDataEntrada(Instant.ofEpochMilli(request.getDataEntrada()))) {
            errors.rejectValue("dataEntrada",
                    "Data entrada inválida");
        }
    }

    private boolean validarDataEntrada(Instant dataEntrada) {
        Instant dataAtual = Instant.now().truncatedTo(DAYS);
        return dataEntrada.isAfter(dataAtual) || dataEntrada.isBefore(dataAtual);
    }
    

}