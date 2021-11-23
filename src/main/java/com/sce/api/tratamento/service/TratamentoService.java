package com.sce.api.tratamento.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.deposito.model.DepositoResponse;
import com.sce.api.imovel.model.ImovelResponse;
import com.sce.api.tratamento.exception.TratamentoApiException;
import com.sce.api.tratamento.model.TratamentoResponse;
import com.sce.api.tratamento.model.TratamentoVo;
import com.sce.api.vistoria.vo.VistoriaResponse;
import com.sce.persistence.deposito.entity.Deposito;
import com.sce.persistence.imovel.entity.Imovel;
import com.sce.persistence.tratamento.entity.Tratamento;
import com.sce.persistence.tratamento.repository.TratamentoRepository;
import com.sce.persistence.vistoria.entity.Vistoria;

@Service
public class TratamentoService {

    @Autowired
    private TratamentoRepository tratamentoRepository;
    @Autowired
    private TratamentoDataValidator dataValidator;

    public List<TratamentoResponse> listarTodos() {
        List<TratamentoResponse> response = new ArrayList<>();
        StreamSupport.stream(tratamentoRepository.findAll().spliterator(), false)
                .forEach(tratamento -> response.add(gerarTratamentoResponse(tratamento)));
        return response;
    }

    public void remover(Long id) throws TratamentoApiException {
        Tratamento tratamento = buscarTratamentoById(id);
        tratamentoRepository.delete(tratamento);
    }
    
    public void removerByVistoriaId(Long vistoriaId) throws TratamentoApiException {
        Optional<Tratamento> tratamento = tratamentoRepository.findByVistoriaId(vistoriaId);
        if (tratamento.isPresent()) {
            tratamentoRepository.delete(tratamento.get());
        }
    }

    public TratamentoResponse atualizar(TratamentoVo vo, Long id) throws TratamentoApiException {
        buscarTratamentoById(id);
        Tratamento tratamento = tratamentoRepository.save(gerarTratamento(vo, id));
        return gerarTratamentoResponse(tratamento);
    }

    public TratamentoResponse pesquisarTratamentoById(Long id) throws TratamentoApiException {
        Tratamento tratamento = buscarTratamentoById(id);
        return gerarTratamentoResponse(tratamento);
    }

    public TratamentoResponse salvar(TratamentoVo vo) throws TratamentoApiException {
        dataValidator.validarDeposito(vo.getDepositoId());
        dataValidator.validarVistoria(vo.getVistoriaId());
        Tratamento tratamento = tratamentoRepository.save(gerarTratamento(vo));
        return gerarTratamentoResponse(tratamento);
    }

    private Tratamento buscarTratamentoById(long id) throws TratamentoApiException {
        Optional<Tratamento> tratamento = tratamentoRepository.findById(id);
        if (tratamento.isPresent()) {
            return tratamento.get();
        } else {
            throw new TratamentoApiException("Tratamento não existe na base de dados.", NOT_FOUND);
        }
    }
    

    private TratamentoResponse gerarTratamentoResponse(Tratamento tratamento) {
        DepositoResponse depositoResponse = gerarDepositoResponse(tratamento.getDeposito());
        VistoriaResponse vistoriaResponse = gerarVistoriaResponse(tratamento.getVistoria());
        return TratamentoResponse.builder()
                .deposito(depositoResponse)
                .quantidadeCargasAdulticida(tratamento.getQuantidadeCargasAdulticida())
                .quantidadeDepositosEliminados(tratamento.getQuantidadeDepositosEliminados())
                .quantidadeLarvicida1(tratamento.getQuantidadeLarvicida1())
                .quantidadeLarvicida2(tratamento.getQuantidadeLarvicida2())
                .quantidadeDepositosLarvicida1(tratamento.getQuantidadeDepositosLarvicida1())
                .quantidadeDepositosLarvicida2(tratamento.getQuantidadeDepositosLarvicida2())
                .tipoAdulticida(tratamento.getTipoAdulticida())
                .tipoLarvicida1(tratamento.getTipoLarvicida1())
                .tipoLarvicida2(tratamento.getTipoLarvicida2())
                .id(tratamento.getId())
                .vistoria(vistoriaResponse)
                .build();
    }

    private DepositoResponse gerarDepositoResponse(Deposito deposito) {
        return DepositoResponse.builder()
                .codigo(deposito.getCodigo())
                .id(deposito.getId())
                .nome(deposito.getNome())
                .build();
    }

    private VistoriaResponse gerarVistoriaResponse(Vistoria vistoria) {
        ImovelResponse imovel = null;
        if (vistoria.getImovel() != null) {
            imovel = gerarImovelResponse(vistoria.getImovel());
        }
        return VistoriaResponse.builder()
                .codigoAtividade(vistoria.getCodigoAtividade())
                .concluida(vistoria.isConcluida())
                .dataVistoria(vistoria.getDataVistoria().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .imovelFechado(vistoria.isImovelFechado())
                .recusada(vistoria.isRecusada())
                .tipoVisita(vistoria.getTipoVisita())
                .tipo(vistoria.getTipo())
                .id(vistoria.getId())
                .imovel(imovel)
                .build();
    }

    private ImovelResponse gerarImovelResponse(Imovel imovel) {
        return ImovelResponse.builder()
                .bairro(imovel.getBairro())
                .cidade(imovel.getCidade())
                .complemento(imovel.getComplemento())
                .lado(imovel.getLado())
                .numero(imovel.getNumero())
                .numeroQuarteirao(imovel.getNumeroQuarteirao())
                .rua(imovel.getRua())
                .tipo(imovel.getTipo())
                .zona(imovel.getZona())
                .id(imovel.getId())
                .cep(imovel.getCep())
                .build();
    }

    private Tratamento gerarTratamento(TratamentoVo vo) {
        Deposito deposito = gerarDeposito(vo);
        Vistoria vistoria = gerarVistoria(vo);
        return Tratamento.builder()
                .quantidadeCargasAdulticida(vo.getQuantidadeCargasAdulticida())
                .quantidadeDepositosEliminados(vo.getQuantidadeDepositosEliminados())
                .quantidadeLarvicida1(vo.getQuantidadeLarvicida1())
                .quantidadeLarvicida2(vo.getQuantidadeLarvicida2())
                .tipoAdulticida(vo.getTipoAdulticida())
                .tipoLarvicida1(vo.getTipoLarvicida1())
                .tipoLarvicida2(vo.getTipoLarvicida2())
                .deposito(deposito)
                .vistoria(vistoria)
                .build();
    }

    private Tratamento gerarTratamento(TratamentoVo vo, long id) {
        Deposito deposito = gerarDeposito(vo);
        Vistoria vistoria = gerarVistoria(vo);
        return Tratamento.builder()
                .quantidadeCargasAdulticida(vo.getQuantidadeCargasAdulticida())
                .quantidadeDepositosEliminados(vo.getQuantidadeDepositosEliminados())
                .quantidadeLarvicida1(vo.getQuantidadeLarvicida1())
                .quantidadeLarvicida2(vo.getQuantidadeLarvicida2())
                .quantidadeDepositosLarvicida1(vo.getQuantidadeDepositosLarvicida1())
                .quantidadeDepositosLarvicida2(vo.getQuantidadeDepositosLarvicida2())
                .tipoAdulticida(vo.getTipoAdulticida())
                .tipoLarvicida1(vo.getTipoLarvicida1())
                .tipoLarvicida2(vo.getTipoLarvicida2())
                .deposito(deposito)
                .vistoria(vistoria)
                .id(id)
                .build();
    }

    private Vistoria gerarVistoria(TratamentoVo vo) {
        return Vistoria.builder()
                .id(vo.getVistoriaId())
                .build();
    }

    private Deposito gerarDeposito(TratamentoVo vo) {
        return Deposito.builder()
                .id(vo.getDepositoId())
                .build();
    }

}
