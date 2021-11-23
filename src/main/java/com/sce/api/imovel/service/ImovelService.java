package com.sce.api.imovel.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.imovel.exception.ImovelApiException;
import com.sce.api.imovel.model.ImovelResponse;
import com.sce.api.imovel.model.ImovelVo;
import com.sce.persistence.imovel.entity.Imovel;
import com.sce.persistence.imovel.repository.ImovelRepository;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public List<ImovelResponse> listarTodos() {
        List<ImovelResponse> response = new ArrayList<>();
        StreamSupport.stream(imovelRepository.findAll().spliterator(), false).forEach(imovel -> response.add(gerarImovelResponse(imovel)));
        return response;
    }

    public ImovelResponse salvar(ImovelVo vo) {
        Imovel imovel = imovelRepository.save(gerarImovel(vo));
        return gerarImovelResponse(imovel);
    }

    public ImovelResponse atualizar(ImovelVo vo, Long id) throws ImovelApiException {
        buscarImovelById(id);
        Imovel imovel = imovelRepository.save(gerarImovel(vo, id));
        return gerarImovelResponse(imovel);
    }

    public void remover(Long id) throws ImovelApiException {
        Imovel imovel = buscarImovelById(id);
        imovelRepository.delete(imovel);
    }

    public ImovelResponse buscarById(Long id) throws ImovelApiException {
        Imovel imovel = buscarImovelById(id);
        return gerarImovelResponse(imovel);
    }

    private Imovel buscarImovelById(long id) throws ImovelApiException {
        Optional<Imovel> imovel = imovelRepository.findById(id);
        if (imovel.isPresent()) {
            return imovel.get();
        } else {
            throw new ImovelApiException("Imovel não existe na base de dados.", NOT_FOUND);
        }
    }

    private Imovel gerarImovel(ImovelVo vo) {
        return Imovel.builder()
                .bairro(vo.getBairro())
                .cidade(vo.getCidade())
                .complemento(vo.getComplemento())
                .lado(vo.getLado())
                .numero(vo.getNumero())
                .numeroQuarteirao(vo.getNumeroQuarteirao())
                .rua(vo.getRua())
                .tipo(vo.getTipo())
                .zona(vo.getZona())
                .cep(vo.getCep())
                .build();
    }

    private Imovel gerarImovel(ImovelVo vo, long id) {
        return Imovel.builder()
                .bairro(vo.getBairro())
                .cidade(vo.getCidade())
                .complemento(vo.getComplemento())
                .lado(vo.getLado())
                .numero(vo.getNumero())
                .numeroQuarteirao(vo.getNumeroQuarteirao())
                .rua(vo.getRua())
                .tipo(vo.getTipo())
                .zona(vo.getZona())
                .cep(vo.getCep())
                .id(id)
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
}