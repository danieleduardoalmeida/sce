package com.sce.api.analise.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.analise.exception.AnaliseApiException;
import com.sce.api.analise.model.AnaliseResponse;
import com.sce.api.analise.model.AnaliseVo;
import com.sce.persistence.analise.entity.Analise;
import com.sce.persistence.analise.repository.AnaliseRepository;

@Service
public class AnaliseService {

    @Autowired
    private AnaliseRepository analiseRepository;

    public List<AnaliseResponse> listarTodos() {
        List<AnaliseResponse> response = new ArrayList<>();
        StreamSupport.stream(analiseRepository.findAll().spliterator(), false).forEach(analise -> response.add(gerarAnaliseResponse(analise)));
        return response;
    }

    public AnaliseResponse salvar(AnaliseVo vo) {
        Analise analise = analiseRepository.save(gerarAnalise(vo));
        return gerarAnaliseResponse(analise);
    }

    public AnaliseResponse atualizar(AnaliseVo vo, Long id) throws AnaliseApiException {
        buscarAnaliseById(id);
        Analise analise = analiseRepository.save(gerarAnalise(vo, id));
        return gerarAnaliseResponse(analise);
    }

    public void remover(Long id) throws AnaliseApiException {
        Analise analise = buscarAnaliseById(id);
        analiseRepository.delete(analise);
    }

    public AnaliseResponse pesquisarAnaliseById(Long id) throws AnaliseApiException {
        Analise analise = buscarAnaliseById(id);
        return gerarAnaliseResponse(analise);
    }
    
    public List<AnaliseResponse> listarAnalisesAbertas() {
        List<AnaliseResponse> response = new ArrayList<>();
        StreamSupport.stream(analiseRepository.findAllOpenAnalises().spliterator(), false).forEach(analise -> response.add(gerarAnaliseResponse(analise)));
        return response;
    }

    private Analise buscarAnaliseById(long id) throws AnaliseApiException {
        Optional<Analise> analise = analiseRepository.findById(id);
        if (analise.isPresent()) {
            return analise.get();
        } else {
            throw new AnaliseApiException("Analise não existe na base de dados.", NOT_FOUND);
        }
    }

    private Analise gerarAnalise(AnaliseVo vo) {
        return Analise.builder()
                .dataConclusao(LocalDateTime.ofInstant(Instant.ofEpochMilli(vo.getDataConclusao()), TimeZone.getDefault().toZoneId()))
                .dataEntrada(LocalDateTime.ofInstant(Instant.ofEpochMilli(vo.getDataEntrada()), TimeZone.getDefault().toZoneId()))
                .laboratorio(vo.getLaboratorio())
                .laboratorista(vo.getLaboratorista())
                .build();
    }

    private Analise gerarAnalise(AnaliseVo vo, Long id) {
        return Analise.builder()
                .dataConclusao(LocalDateTime.ofInstant(Instant.ofEpochMilli(vo.getDataConclusao()), TimeZone.getDefault().toZoneId()))
                .dataEntrada(LocalDateTime.ofInstant(Instant.ofEpochMilli(vo.getDataEntrada()), TimeZone.getDefault().toZoneId()))
                .laboratorio(vo.getLaboratorio())
                .laboratorista(vo.getLaboratorista())
                .id(id)
                .build();
    }

    private AnaliseResponse gerarAnaliseResponse(Analise analise) {
        return AnaliseResponse.builder()
                .dataConclusao(analise.getDataConclusao().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .dataEntrada(analise.getDataEntrada().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .id(analise.getId())
                .laboratorio(analise.getLaboratorio())
                .laboratorista(analise.getLaboratorista())
                .build();
    }
}