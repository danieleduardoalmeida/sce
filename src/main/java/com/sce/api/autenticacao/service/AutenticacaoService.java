package com.sce.api.autenticacao.service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.autenticacao.exception.AutenticacaoApiException;
import com.sce.api.autenticacao.model.AutenticacaoResponse;
import com.sce.api.autenticacao.model.AutenticacaoVo;
import com.sce.persistence.usuario.entity.Usuario;
import com.sce.persistence.usuario.repository.UsuarioRepository;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AutenticacaoResponse autenticarUsuario(AutenticacaoVo vo) throws AutenticacaoApiException  {
        String senhaCriptografada = gerarMD5Hash(vo.getSenha());
        Optional<Usuario> usuario = usuarioRepository.findByLoginAndSenha(vo.getUsuario().toLowerCase(), senhaCriptografada);
        if (usuario.isEmpty()) {
            throw new AutenticacaoApiException("Usuário ou senha invalido(s).", UNAUTHORIZED);
        }
        return buildAuthenticationResponse(vo.getUsuario());
    }

    private AutenticacaoResponse buildAuthenticationResponse(String usuario) {
        return AutenticacaoResponse.builder()
                .usuario(usuario)
                .build();
    }

    private String gerarMD5Hash(String senha) throws AutenticacaoApiException  {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(senha.getBytes(), 0, senha.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new AutenticacaoApiException("Não foi possivel criptografar a senha.", BAD_REQUEST);
        }
    }

}
