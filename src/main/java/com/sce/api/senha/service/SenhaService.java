package com.sce.api.senha.service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.senha.exception.SenhaApiException;
import com.sce.api.senha.model.SenhaVo;
import com.sce.persistence.usuario.entity.Usuario;
import com.sce.persistence.usuario.repository.UsuarioRepository;

@Service
public class SenhaService {

    @Autowired
    private UsuarioRepository repository;

    public void alterarSenha(SenhaVo vo) throws SenhaApiException {
        Usuario usuario = buscarUsuario(vo.getUsuario().toLowerCase());
        String senhaAntiga = gerarMD5Hash(vo.getSenhaAntiga());
        if (validarSenhaAntiga(usuario.getId(), senhaAntiga) && validarSenhaNova(vo.getSenhaAntiga(), vo.getSenhaNova())) {
            String novaSenhaCriptografada = gerarMD5Hash(vo.getSenhaNova());
            repository.updateSenha(novaSenhaCriptografada, usuario.getId());
        }
    }

    private Usuario buscarUsuario(String login) throws SenhaApiException {
        Optional<Usuario> usuario = repository.findByLogin(login);
        if (usuario.isEmpty()) {
            throw new SenhaApiException("Usuário não encontrado", NOT_FOUND);
        }
        return usuario.get();
    }

    private boolean validarSenhaAntiga(long id, String senhaAntiga) throws SenhaApiException {
        if (repository.findByIdAndSenha(id, senhaAntiga).isEmpty()) {
            throw new SenhaApiException("A senha antiga é inválida", BAD_REQUEST);
        }
        return true;
    }
    
    private boolean validarSenhaNova(String senhaAntiga, String senhaNova) throws SenhaApiException {
        if (senhaAntiga.equals(senhaNova)) {
            throw new SenhaApiException("A senha antiga é igual a senha nova", BAD_REQUEST);
        }
        return true;
    }

    private String gerarMD5Hash(String senha) throws SenhaApiException {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(senha.getBytes(), 0, senha.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new SenhaApiException("Não foi possivel criptografar a senha.", BAD_REQUEST);
        }
    }

}
