package com.sce.api.usuario.service;

import static com.sce.persistence.usuario.entity.Usuario.TipoUsuario.ADMINISTRADOR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sce.api.usuario.exception.UsuarioApiException;
import com.sce.api.usuario.model.UsuarioResponse;
import com.sce.api.usuario.model.UsuarioVo;
import com.sce.persistence.usuario.entity.Usuario;
import com.sce.persistence.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioDataValidator dataValidator;

    public UsuarioResponse criar(UsuarioVo vo) throws NoSuchAlgorithmException, UsuarioApiException {
        dataValidator.validarCriacaoUsuario(vo);
        Usuario usuario = gerarUsuario(vo);
        return gerarUsuarioResponse(usuarioRepository.save(usuario));
    }

    private Usuario gerarUsuario(UsuarioVo vo) throws NoSuchAlgorithmException {
        return Usuario.builder()
                .email(vo.getEmail())
                .login(vo.getLogin().toLowerCase())
                .nome(vo.getNome())
                .senha(gerarMD5Hash(vo.getSenha()))
                .telefone(vo.getTelefone())
                .tipo(vo.getTipo())
                .build();
    }

    private String gerarMD5Hash(String senha) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(senha.getBytes(), 0, senha.length());
        return new BigInteger(1, m.digest()).toString(16);
    }

    public UsuarioResponse atualizar(UsuarioVo vo, long id) throws NoSuchAlgorithmException, UsuarioApiException {
        dataValidator.validateAtualizacao(vo, id);
        Usuario usuario = gerarUsuario(vo, id);
        return gerarUsuarioResponse(usuarioRepository.save(usuario));
    }

    public List<UsuarioResponse> listarTodos() {
        List<UsuarioResponse> response = new ArrayList<>();
        List<Usuario> usuarios = usuarioRepository.findAllExceptType(ADMINISTRADOR.name());
        usuarios.stream().forEach(usuario -> response.add(gerarUsuarioResponse(usuario)));
        return response;
    }

    public UsuarioResponse buscarUsuarioById(Long id) throws UsuarioApiException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return gerarUsuarioResponse(usuario.get());
        } else {
            throw new UsuarioApiException("Usuário não existe na base de dados.", NOT_FOUND);
        }
    }
    
    public UsuarioResponse buscarUsuarioByLogin(String login) throws UsuarioApiException {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
        if (usuario.isPresent()) {
            return gerarUsuarioResponse(usuario.get());
        } else {
            throw new UsuarioApiException("Usuário não existe na base de dados.", NOT_FOUND);
        }
    }
    
    public void remover(Long id) throws UsuarioApiException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
        } else {
            throw new UsuarioApiException("Usuário não existe na base de dados.", NOT_FOUND);
        }
    }




    private Usuario gerarUsuario(UsuarioVo vo, long id) throws NoSuchAlgorithmException {
        return Usuario.builder()
                .email(vo.getEmail())
                .login(vo.getLogin().toLowerCase())
                .nome(vo.getNome())
                .senha(gerarMD5Hash(vo.getSenha()))
                .telefone(vo.getTelefone())
                .tipo(vo.getTipo())
                .id(id)
                .build();
    }

    private UsuarioResponse gerarUsuarioResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .email(usuario.getEmail())
                .login(usuario.getLogin())
                .nome(usuario.getNome())
                .telefone(usuario.getTelefone())
                .tipo(usuario.getTipo())
                .id(usuario.getId())
                .build();
    }



    
}
