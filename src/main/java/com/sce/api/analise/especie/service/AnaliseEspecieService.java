package com.sce.api.analise.especie.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.analise.especie.exception.AnaliseEspecieApiException;
import com.sce.api.analise.especie.model.AnaliseEspecieResponse;
import com.sce.api.analise.especie.model.AnaliseEspecieVo;
import com.sce.api.analise.model.AnaliseResponse;
import com.sce.api.especie.model.EspecieResponse;
import com.sce.persistence.analise.entity.Analise;
import com.sce.persistence.analise.especie.entity.AnaliseEspecie;
import com.sce.persistence.analise.especie.entity.AnaliseEspecieId;
import com.sce.persistence.analise.especie.repository.AnaliseEspecieRepository;
import com.sce.persistence.especie.entity.Especie;

@Service
public class AnaliseEspecieService {

    @Autowired
    private AnaliseEspecieRepository analiseEspecieRepository;
    @Autowired
    private AnaliseEspecieDataValidator dataValidator;

    public List<AnaliseEspecieResponse> listarTodos() {
        List<AnaliseEspecieResponse> response = new ArrayList<>();
        StreamSupport.stream(analiseEspecieRepository.findAll().spliterator(), false)
                .forEach(analiseEspecie -> response.add(gerarAnaliseEspecieResponse(analiseEspecie)));
        return response;
    }

    public AnaliseEspecieResponse salvar(AnaliseEspecieVo vo) throws AnaliseEspecieApiException {
        dataValidator.validarAnalise(vo.getAnaliseId());
        dataValidator.validarEspecie(vo.getEspecieId());
        AnaliseEspecie analiseEspecie = analiseEspecieRepository.save(gerarAnaliseEspecie(vo));
        return gerarAnaliseEspecieResponse(analiseEspecie);
    }

    public List<AnaliseEspecieResponse> pesquisarAnaliseEspecieByIdAnalise(long id) {
        List<AnaliseEspecieResponse> response = new ArrayList<>();
        StreamSupport.stream(analiseEspecieRepository.findAllByIdAnaliseId(id).spliterator(), false)
                .forEach(analiseEspecie -> response.add(gerarAnaliseEspecieResponse(analiseEspecie)));
        return response;
    }

    public void remover(Long id) {
        List<AnaliseEspecie> analisesEspecie = new ArrayList<>();
        analisesEspecie = analiseEspecieRepository.findAllByIdAnaliseId(id);
        analisesEspecie.forEach(analiseEspecie -> analiseEspecieRepository.delete(analiseEspecie));
    }

    private AnaliseEspecieResponse gerarAnaliseEspecieResponse(AnaliseEspecie analiseEspecie) {
        AnaliseResponse analiseResponse = gerarAnaliseResponse(analiseEspecie.getId().getAnalise());
        EspecieResponse especieResponse = gerarEspecieResponse(analiseEspecie.getId().getEspecie());
        return AnaliseEspecieResponse.builder()
                .analise(analiseResponse)
                .especie(especieResponse)
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

    private AnaliseEspecie gerarAnaliseEspecie(AnaliseEspecieVo vo) {
        AnaliseEspecieId id = gerarAnaliseEspecieId(vo);
        return AnaliseEspecie.builder()
                .id(id).build();
    }

    private AnaliseEspecieId gerarAnaliseEspecieId(AnaliseEspecieVo vo) {
        Analise analise = gerarAnalise(vo.getAnaliseId());
        Especie especie = gerarEspecie(vo.getEspecieId());
        return AnaliseEspecieId.builder()
                .analise(analise)
                .especie(especie)
                .build();
    }

    private Especie gerarEspecie(long especieId) {
        return Especie.builder()
                .id(especieId)
                .build();
    }

    private Analise gerarAnalise(long analiseId) {
        return Analise.builder()
                .id(analiseId)
                .build();
    }
}