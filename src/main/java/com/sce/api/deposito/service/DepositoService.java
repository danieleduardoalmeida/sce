package com.sce.api.deposito.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.deposito.exception.DepositoApiException;
import com.sce.api.deposito.model.DepositoResponse;
import com.sce.persistence.deposito.entity.Deposito;
import com.sce.persistence.deposito.repository.DepositoRepository;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository depositoRepository;

    public List<DepositoResponse> listarTodos() {
        List<DepositoResponse> response = new ArrayList<>();
        List<Deposito> depositos = StreamSupport.stream(depositoRepository.findAll().spliterator(), false).collect(Collectors.toList());
        depositos.stream().forEach(deposito -> response.add(gerarDepositoResponse(deposito)));
        return response;
    }
    
    public DepositoResponse pesquisarDepositoById(Long id) throws DepositoApiException {
        Deposito deposito = buscarDepositoById(id);
        return gerarDepositoResponse(deposito);
    }
    
    private Deposito buscarDepositoById(long id) throws DepositoApiException {
        Optional<Deposito> deposito = depositoRepository.findById(id);
        if (deposito.isPresent()) {
            return deposito.get();
        } else {
            throw new DepositoApiException("Depósito não existe na base de dados.", NOT_FOUND);
        }
    }

    private DepositoResponse gerarDepositoResponse(Deposito deposito) {
        return DepositoResponse.builder()
                .id(deposito.getId())
                .nome(deposito.getNome())
                .codigo(deposito.getCodigo())
                .build();
    }

    
}
