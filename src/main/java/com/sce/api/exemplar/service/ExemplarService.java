package com.sce.api.exemplar.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.exemplar.exception.ExemplarApiException;
import com.sce.api.exemplar.model.ExemplarResponse;
import com.sce.persistence.exemplar.entity.Exemplar;
import com.sce.persistence.exemplar.repository.ExemplarRepository;

@Service
public class ExemplarService {
	@Autowired
	private ExemplarRepository exemplarRepository;
	
	public List<ExemplarResponse> listarTodos() {
        List<ExemplarResponse> response = new ArrayList<>();
        List<Exemplar> exemplares = StreamSupport.stream(exemplarRepository.findAll().spliterator(), false).collect(Collectors.toList());
        exemplares.stream().forEach(exemplar -> response.add(gerarExemplarResponse(exemplar)));
        return response;
    }
	public ExemplarResponse pesquisarExemplarById(Long id) throws ExemplarApiException {
        Exemplar exemplar = buscarExemplarById(id);
        return gerarExemplarResponse(exemplar);
    }
	
	private Exemplar buscarExemplarById(long id) throws ExemplarApiException {
        Optional<Exemplar> exemplar = exemplarRepository.findById(id);
        if (exemplar.isPresent()) {
            return exemplar.get();
        } else {
            throw new ExemplarApiException("Exemplar não existe na base de dados.", NOT_FOUND);
        }
    }

    private ExemplarResponse gerarExemplarResponse(Exemplar exemplar) {
        return ExemplarResponse.builder()
                .id(exemplar.getId())
                .nome(exemplar.getNome())
                .build();
    }

    
	
	
}
