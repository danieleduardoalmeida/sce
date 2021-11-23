package com.sce.api.vistoria.service;

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

import com.sce.api.imovel.model.ImovelResponse;
import com.sce.api.vistoria.exception.VistoriaApiException;
import com.sce.api.vistoria.vo.VistoriaResponse;
import com.sce.api.vistoria.vo.VistoriaVo;
import com.sce.persistence.imovel.entity.Imovel;
import com.sce.persistence.usuario.entity.Usuario;
import com.sce.persistence.vistoria.entity.Vistoria;
import com.sce.persistence.vistoria.repository.VistoriaRepository;

@Service
public class VistoriaService {

    @Autowired
    private VistoriaRepository vistoriaRepository;
    @Autowired
    private VistoriaDataValidator dataValidator;

    public List<VistoriaResponse> listarTodos() {
        List<VistoriaResponse> response = new ArrayList<>();
        StreamSupport.stream(vistoriaRepository.findAll().spliterator(), false).forEach(vistoria -> response.add(gerarVistoriaResponse(vistoria)));
        return response;
    }
    
    public VistoriaResponse atualizar(VistoriaVo vo, Long id) throws VistoriaApiException {
        buscarVistoriaById(id);
        Vistoria vistoria = vistoriaRepository.save(gerarVistoria(vo, id));
        return gerarVistoriaResponse(vistoria);
    }

    public VistoriaResponse pesquisarVistoriaById(Long id) throws VistoriaApiException {
        Vistoria vistoria = buscarVistoriaById(id);
        return gerarVistoriaResponse(vistoria);
    }

    public void remover(Long id) throws VistoriaApiException {
        Vistoria vistoria = buscarVistoriaById(id);
        vistoriaRepository.delete(vistoria);
    }

    public VistoriaResponse salvar(VistoriaVo vo) throws VistoriaApiException {
        dataValidator.validarImovel(vo.getImovelId());
        dataValidator.validarUsuario(vo.getUsuarioId());
        Vistoria vistoria = vistoriaRepository.save(gerarVistoria(vo));
        return gerarVistoriaResponse(vistoria);
    }
    
    private Vistoria buscarVistoriaById(long id) throws VistoriaApiException {
        Optional<Vistoria> vistoria = vistoriaRepository.findById(id);
        if (vistoria.isPresent()) {
            return vistoria.get();
        } else {
            throw new VistoriaApiException("Vistoria não existe na base de dados.", NOT_FOUND);
        }
    }

    private Vistoria gerarVistoria(VistoriaVo vo) {
        Usuario usuario = gerarUsuario(vo);
        Imovel imovel = gerarImovel(vo);
        return Vistoria.builder()
                .codigoAtividade(vo.getCodigoAtividade())
                .concluida(vo.isConcluida())
                .dataVistoria(LocalDateTime.ofInstant(Instant.ofEpochMilli(vo.getDataVistoria()), TimeZone.getDefault().toZoneId()))
                .imovelFechado(vo.isImovelFechado())
                .recusada(vo.isRecusada())
                .tipoVisita(vo.getTipoVisita())
                .tipo(vo.getTipo())
                .usuario(usuario)
                .imovel(imovel)
                .build();
    }
    
    private Vistoria gerarVistoria(VistoriaVo vo, long id) {
        Usuario usuario = gerarUsuario(vo);
        Imovel imovel = gerarImovel(vo);
        return Vistoria.builder()
                .codigoAtividade(vo.getCodigoAtividade())
                .concluida(vo.isConcluida())
                .dataVistoria(LocalDateTime.ofInstant(Instant.ofEpochMilli(vo.getDataVistoria()), TimeZone.getDefault().toZoneId()))
                .imovelFechado(vo.isImovelFechado())
                .recusada(vo.isRecusada())
                .tipoVisita(vo.getTipoVisita())
                .tipo(vo.getTipo())
                .usuario(usuario)
                .imovel(imovel)
                .id(id)
                .build();
    }

    private Imovel gerarImovel(VistoriaVo vo) {
        return Imovel.builder()
                .id(vo.getImovelId())
                .build();
    }

    private Usuario gerarUsuario(VistoriaVo vo) {
        return Usuario.builder()
                .id(vo.getUsuarioId())
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
                .cep(imovel.getCep())
                .id(imovel.getId())
                .build();
    }

    private VistoriaResponse gerarVistoriaResponse(Vistoria vistoria) {
        return VistoriaResponse.builder()
                .codigoAtividade(vistoria.getCodigoAtividade())
                .concluida(vistoria.isConcluida())
                .dataVistoria(vistoria.getDataVistoria().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .imovelFechado(vistoria.isImovelFechado())
                .recusada(vistoria.isRecusada())
                .usuarioId(vistoria.getUsuario().getId())
                .imovel(gerarImovelResponse(vistoria.getImovel()))
                .tipoVisita(vistoria.getTipoVisita())
                .tipo(vistoria.getTipo())
                .id(vistoria.getId())
                .build();
    }

    
}
