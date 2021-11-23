package com.sce.api.vistoria.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.vistoria.exception.VistoriaApiException;
import com.sce.persistence.imovel.repository.ImovelRepository;
import com.sce.persistence.usuario.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VistoriaDataValidator {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ImovelRepository imovelRepository;
    
    public void validarUsuario(long usuarioId) throws VistoriaApiException {
        if (!usuarioRepository.findById(usuarioId).isPresent()) {
            log.warn("Usuario não existe.");
            throw new VistoriaApiException("Usuário não existe.", NOT_FOUND);
        }
    }
    
    public void validarImovel(long imovelId) throws VistoriaApiException {
        if (!imovelRepository.findById(imovelId).isPresent()) {
            log.warn("Imóvel não existe.");
            throw new VistoriaApiException("Imóvel não existe.", NOT_FOUND);
        }
    }

}
