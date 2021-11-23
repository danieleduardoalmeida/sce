package com.sce.api.amostra.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.amostra.exception.AmostraApiException;
import com.sce.api.amostra.model.AmostraResponse;
import com.sce.api.amostra.model.AmostraVo;
import com.sce.persistence.amostra.entity.Amostra;
import com.sce.persistence.amostra.repository.AmostraRepository;
import com.sce.persistence.analise.entity.Analise;
import com.sce.persistence.deposito.entity.Deposito;
import com.sce.persistence.vistoria.entity.Vistoria;

@Service
public class AmostraService {

    @Autowired
    private AmostraRepository amostraRepository;
    @Autowired
    private AmostraDataValidator dataValidator;

    public List<AmostraResponse> listarTodos() {
        List<AmostraResponse> response = new ArrayList<>();
        StreamSupport.stream(amostraRepository.findAll().spliterator(), false).forEach(amostra -> response.add(gerarAmostraResponse(amostra)));
        return response;
    }

    public AmostraResponse salvar(AmostraVo vo) throws AmostraApiException {
        dataValidator.validarAnalise(vo.getAnaliseId());
        dataValidator.validarDeposito(vo.getDepositoId());
        dataValidator.validarVistoria(vo.getVistoriaId());
        Amostra a = gerarAmostra(vo);
        Amostra amostra = amostraRepository.save(a);
        return gerarAmostraResponse(amostra);
    }

    public AmostraResponse atualizar(AmostraVo vo, Long id) throws AmostraApiException {
        dataValidator.validarAnalise(vo.getAnaliseId());
        dataValidator.validarDeposito(vo.getDepositoId());
        dataValidator.validarVistoria(vo.getVistoriaId());
        buscarAmostraById(id);
        Amostra amostra = amostraRepository.save(gerarAmostra(vo, id));
        return gerarAmostraResponse(amostra);
    }

    public void remover(Long id) throws AmostraApiException {
        Amostra amostra = buscarAmostraById(id);
        amostraRepository.delete(amostra);
    }

    public AmostraResponse pesquisarAmostraById(Long id) throws AmostraApiException {
        Amostra amostra = buscarAmostraById(id);
        return gerarAmostraResponse(amostra);
    }

    private Amostra buscarAmostraById(long id) throws AmostraApiException {
        Optional<Amostra> amostra = amostraRepository.findById(id);
        if (amostra.isPresent()) {
            return amostra.get();
        } else {
            throw new AmostraApiException("Amostra não existe na base de dados.", NOT_FOUND);
        }
    }

    private AmostraResponse gerarAmostraResponse(Amostra amostra) {
        return AmostraResponse.builder()
                .analiseId(amostra.getAnalise().getId())
                .depositoId(amostra.getDeposito().getId())
                .quantidadeTubitos(amostra.getQuantidadeTubitos())
                .vistoriaId(amostra.getVistoria().getId())
                .tipo(amostra.getTipo())
                .id(amostra.getId())
                .build();
    }

    private Amostra gerarAmostra(AmostraVo vo, long id) {
        Analise analise = gerarAnalise(vo);
        Deposito deposito = gerarDeposito(vo);
        Vistoria vistoria = buildVistoria(vo);
        return Amostra.builder()
                .analise(analise)
                .deposito(deposito)
                .vistoria(vistoria)
                .tipo(vo.getTipo())
                .quantidadeTubitos(vo.getQuantidadeTubitos())
                .id(id)
                .build();
    }

    private Amostra gerarAmostra(AmostraVo vo) {
        Analise analise = gerarAnalise(vo);
        Deposito deposito = gerarDeposito(vo);
        Vistoria vistoria = buildVistoria(vo);
        return Amostra.builder()
                .analise(analise)
                .deposito(deposito)
                .vistoria(vistoria)
                .tipo(vo.getTipo())
                .quantidadeTubitos(vo.getQuantidadeTubitos())
                .build();
    }

    private Vistoria buildVistoria(AmostraVo vo) {
        return Vistoria.builder()
                .id(vo.getVistoriaId())
                .build();
    }

    private Deposito gerarDeposito(AmostraVo vo) {
        return Deposito.builder()
                .id(vo.getDepositoId())
                .build();
    }

    private Analise gerarAnalise(AmostraVo vo) {
        return Analise.builder()
                .id(vo.getAnaliseId())
                .build();

    }

}
