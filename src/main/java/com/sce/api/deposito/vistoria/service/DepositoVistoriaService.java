package com.sce.api.deposito.vistoria.service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.deposito.model.DepositoResponse;
import com.sce.api.deposito.vistoria.exception.DepositoVistoriaApiException;
import com.sce.api.deposito.vistoria.model.DepositoVistoriaResponse;
import com.sce.api.deposito.vistoria.model.DepositoVistoriaVo;
import com.sce.api.vistoria.vo.VistoriaResponse;
import com.sce.persistence.deposito.entity.Deposito;
import com.sce.persistence.deposito.vistoria.entity.DepositoVistoria;
import com.sce.persistence.deposito.vistoria.entity.DepositoVistoriaId;
import com.sce.persistence.deposito.vistoria.repository.DepositoVistoriaRepository;
import com.sce.persistence.vistoria.entity.Vistoria;

@Service
public class DepositoVistoriaService {

    @Autowired
    private DepositoVistoriaRepository depositoVistoriaRepository;
    @Autowired
    private DepositoVistoriaDataValidator dataValidator;

    public List<DepositoVistoriaResponse> listarTodos() {
        List<DepositoVistoriaResponse> response = new ArrayList<>();
        StreamSupport.stream(depositoVistoriaRepository.findAll().spliterator(), false)
                .forEach(depositoVistoria -> response.add(gerarDepositoVistoriaResponse(depositoVistoria)));
        return response;
    }

    public List<DepositoVistoriaResponse> pesquisarDepositoVistoriaByIdVistoria(long id) {
        List<DepositoVistoriaResponse> response = new ArrayList<>();
        StreamSupport.stream(depositoVistoriaRepository.findAllByIdVistoriaId(id).spliterator(), false)
                .forEach(depositoVistoria -> response.add(gerarDepositoVistoriaResponse(depositoVistoria)));
        return response;
    }

    public DepositoVistoriaResponse salvar(DepositoVistoriaVo vo) throws DepositoVistoriaApiException {
        dataValidator.validarDeposito(vo.getDepositoId());
        dataValidator.validarVistoria(vo.getVistoriaId());
        DepositoVistoria depositoVistoria = depositoVistoriaRepository.save(gerarDepositoVistoria(vo));
        return gerarDepositoVistoriaResponse(depositoVistoria);
    }
    
    public void remover(Long id) {
        List<DepositoVistoria> depositosVistoria = new ArrayList<>();
        depositosVistoria = depositoVistoriaRepository.findAllByIdVistoriaId(id);
        depositosVistoria.forEach(depositoVistoria -> depositoVistoriaRepository.delete(depositoVistoria));
    }

    private DepositoVistoria gerarDepositoVistoria(DepositoVistoriaVo vo) {
        DepositoVistoriaId id = gerarDepositoVistoriaId(vo);
        return DepositoVistoria.builder()
                .id(id)
                .build();
    }

    private DepositoVistoriaId gerarDepositoVistoriaId(DepositoVistoriaVo vo) {
        Deposito deposito = gerarDeposito(vo);
        Vistoria vistoria = gerarVistoria(vo);
        return DepositoVistoriaId.builder()
                .deposito(deposito)
                .vistoria(vistoria)
                .build();
    }

    private Vistoria gerarVistoria(DepositoVistoriaVo vo) {
        return Vistoria.builder()
                .id(vo.getVistoriaId())
                .build();
    }

    private Deposito gerarDeposito(DepositoVistoriaVo vo) {
        return Deposito.builder()
                .id(vo.getDepositoId())
                .build();
    }

    private DepositoVistoriaResponse gerarDepositoVistoriaResponse(DepositoVistoria depositoVistoria) {
       DepositoResponse depositoResponse = gerarDepositoResponse(depositoVistoria);
       VistoriaResponse vistoriaResponse = gerarVistoriaResponse(depositoVistoria);
        return DepositoVistoriaResponse.builder()
                .deposito(depositoResponse)
                .vistoria(vistoriaResponse)
                .build();
    }

    private DepositoResponse gerarDepositoResponse(DepositoVistoria depositoVistoria) {
        Deposito deposito = depositoVistoria.getId().getDeposito();
        return DepositoResponse.builder()
                .codigo(deposito.getCodigo())
                .id(deposito.getId())
                .nome(deposito.getNome())
                .build();
    }
    
    private VistoriaResponse gerarVistoriaResponse(DepositoVistoria depositoVistoria) {
        Vistoria vistoria = depositoVistoria.getId().getVistoria();
        return VistoriaResponse.builder()
                .id(vistoria.getId())
                .build();
    }
}
