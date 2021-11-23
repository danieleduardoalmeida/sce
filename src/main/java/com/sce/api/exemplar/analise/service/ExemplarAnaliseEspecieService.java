package com.sce.api.exemplar.analise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.analise.model.AnaliseResponse;
import com.sce.api.especie.model.EspecieResponse;
import com.sce.api.exemplar.analise.exception.ExemplarAnaliseApiException;
import com.sce.api.exemplar.analise.model.ExemplarAnaliseEspecieResponse;
import com.sce.api.exemplar.analise.model.ExemplarAnaliseEspecieVo;
import com.sce.api.exemplar.model.ExemplarResponse;
import com.sce.persistence.analise.entity.Analise;
import com.sce.persistence.especie.entity.Especie;
import com.sce.persistence.exemplar.analise.especie.entity.ExemplarAnaliseEspecie;
import com.sce.persistence.exemplar.analise.especie.entity.ExemplarAnaliseEspecieId;
import com.sce.persistence.exemplar.analise.especie.repository.ExemplarAnaliseEspecieRepository;
import com.sce.persistence.exemplar.entity.Exemplar;

@Service
public class ExemplarAnaliseEspecieService {

    @Autowired
    private ExemplarAnaliseEspecieRepository exemplarAnaliseEspecieRepository;
    @Autowired
    private ExemplarAnaliseDataValidator dataValidator;

    public List<ExemplarAnaliseEspecieResponse> listarTodos() {
        List<ExemplarAnaliseEspecieResponse> response = new ArrayList<>();
        StreamSupport.stream(exemplarAnaliseEspecieRepository.findAll().spliterator(), false)
                .forEach(exemplarAnaliseEspecie -> response.add(gerarExemplarAnaliseEspecieResponse(exemplarAnaliseEspecie)));
        return response;
    }

    public ExemplarAnaliseEspecieResponse salvar(ExemplarAnaliseEspecieVo vo) throws ExemplarAnaliseApiException {
        dataValidator.validarAnalise(vo.getAnaliseId());
        dataValidator.validarEspecie(vo.getEspecieId());
        dataValidator.validarExemplar(vo.getExemplarId());
        ExemplarAnaliseEspecie exemplarAnaliseEspecie = exemplarAnaliseEspecieRepository.save(gerarExemplarAnaliseEspecie(vo));
        return gerarExemplarAnaliseEspecieResponse(exemplarAnaliseEspecie);
    }

    public List<ExemplarAnaliseEspecieResponse> pesquisarExemplarAnaliseEspecieByIdAnalise(long id) {
        List<ExemplarAnaliseEspecieResponse> response = new ArrayList<>();
        StreamSupport.stream(exemplarAnaliseEspecieRepository.findAllByIdAnaliseId(id).spliterator(), false)
                .forEach(exemplarAnaliseEspecie -> response.add(gerarExemplarAnaliseEspecieResponse(exemplarAnaliseEspecie)));
        return response;
    }
    
    public void remover(Long id) {
        List<ExemplarAnaliseEspecie> exemplaresAnalisesEspecie = new ArrayList<>();
        exemplaresAnalisesEspecie = exemplarAnaliseEspecieRepository.findAllByIdAnaliseId(id);
        exemplaresAnalisesEspecie.forEach(exemplarAnaliseEspecie -> exemplarAnaliseEspecieRepository.delete(exemplarAnaliseEspecie));
    }

    private ExemplarAnaliseEspecieResponse gerarExemplarAnaliseEspecieResponse(ExemplarAnaliseEspecie exemplarAnaliseEspecie) {
        AnaliseResponse analiseResponse = gerarAnaliseResponse(exemplarAnaliseEspecie.getId().getAnalise());
        EspecieResponse especieResponse = gerarEspecieResponse(exemplarAnaliseEspecie.getId().getEspecie());
        ExemplarResponse exemplarResponse = gerarExemplarResponse(exemplarAnaliseEspecie.getId().getExemplar());
        return ExemplarAnaliseEspecieResponse.builder()
                .analise(analiseResponse)
                .especie(especieResponse)
                .exemplar(exemplarResponse)
                .quantidade(exemplarAnaliseEspecie.getQuantidade())
                .build();
    }

    private ExemplarResponse gerarExemplarResponse(Exemplar exemplar) {
        return ExemplarResponse.builder()
                .id(exemplar.getId())
                .build();
    }

    private EspecieResponse gerarEspecieResponse(Especie especie) {
        return EspecieResponse.builder()
                .id(especie.getId())
                .build();
    }

    private AnaliseResponse gerarAnaliseResponse(Analise analise) {
        return AnaliseResponse.builder()
                .id(analise.getId())
                .build();
    }

    private ExemplarAnaliseEspecie gerarExemplarAnaliseEspecie(ExemplarAnaliseEspecieVo vo) {
        ExemplarAnaliseEspecieId id = gerarExemplarAnaliseEspecieId(vo);
        return ExemplarAnaliseEspecie.builder()
                .id(id)
                .quantidade(vo.getQuantidade())
                .build();
    }

    private ExemplarAnaliseEspecieId gerarExemplarAnaliseEspecieId(ExemplarAnaliseEspecieVo vo) {
        Analise analise = gerarAnalise(vo);
        Especie especie = gerarEspecie(vo);
        Exemplar exemplar = gerarExemplar(vo);
        return ExemplarAnaliseEspecieId.builder()
                .analise(analise)
                .especie(especie)
                .exemplar(exemplar)
                .build();
    }

    private Exemplar gerarExemplar(ExemplarAnaliseEspecieVo vo) {
        return Exemplar.builder()
                .id(vo.getExemplarId())
                .build();
    }

    private Especie gerarEspecie(ExemplarAnaliseEspecieVo vo) {
        return Especie.builder()
                .id(vo.getEspecieId())
                .build();
    }

    private Analise gerarAnalise(ExemplarAnaliseEspecieVo vo) {
        return Analise.builder()
                .id(vo.getAnaliseId())
                .build();
    }
}
