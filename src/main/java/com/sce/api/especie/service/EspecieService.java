package com.sce.api.especie.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.especie.exception.EspecieApiException;
import com.sce.api.especie.model.EspecieResponse;
import com.sce.persistence.especie.entity.Especie;
import com.sce.persistence.especie.repository.EspecieRepository;

@Service
public class EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    public List<EspecieResponse> listarTodos() {
        List<EspecieResponse> response = new ArrayList<>();
        StreamSupport.stream(especieRepository.findAll().spliterator(), false).forEach(especie -> response.add(gerarEspecieResponse(especie)));
        return response;
    }
    
    public EspecieResponse pesquisarEspecieById(Long id) throws EspecieApiException {
        Especie especie = buscarEspecieById(id);
        return gerarEspecieResponse(especie);
    }
    
    private Especie buscarEspecieById(long id) throws EspecieApiException {
        Optional<Especie> especie = especieRepository.findById(id);
        if (especie.isPresent()) {
            return especie.get();
        } else {
            throw new EspecieApiException("Espécie não existe na base de dados.", NOT_FOUND);
        }
    }

    private EspecieResponse gerarEspecieResponse(Especie especie) {
        return EspecieResponse.builder()
                .id(especie.getId())
                .nome(especie.getNome())
                .build();
    }

    
}
