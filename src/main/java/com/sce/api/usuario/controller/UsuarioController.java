package com.sce.api.usuario.controller;

import static com.sce.api.ApiConstants.LOG_REQUEST_INVALIDA;
import static com.sce.api.ApiConstants.PATH_PARAM_ID;
import static com.sce.api.ApiConstants.PATH_PARAM_LOGIN;
import static org.springframework.http.HttpStatus.CREATED;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.usuario.exception.UsuarioApiException;
import com.sce.api.usuario.model.UsuarioResponse;
import com.sce.api.usuario.model.UsuarioVo;
import com.sce.api.usuario.service.UsuarioRequestValidator;
import com.sce.api.usuario.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/sce")
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRequestValidator validator;

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioResponse> postUsuario(@RequestBody UsuarioVo vo) throws NoSuchAlgorithmException, UsuarioApiException {
        checkRequest(vo);
        return ResponseEntity
                .status(CREATED)
                .body(usuarioService.criar(vo));
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<UsuarioResponse> putUsuario(@RequestBody UsuarioVo vo,
                                                      @PathVariable(PATH_PARAM_ID) Long id) throws NoSuchAlgorithmException,
                                                                                            UsuarioApiException {
        checkRequest(vo);
        UsuarioResponse response = usuarioService.atualizar(vo, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(PATH_PARAM_ID) Long id) throws UsuarioApiException {
        usuarioService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioResponse> getUsuario(@PathVariable(PATH_PARAM_ID) Long id) throws UsuarioApiException {
        UsuarioResponse response = usuarioService.buscarUsuarioById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<UsuarioResponse>> getUsuarios() {
        List<UsuarioResponse> response = usuarioService.listarTodos();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/usuario/username/{login}")
    public ResponseEntity<UsuarioResponse> getUsuario(@PathVariable(PATH_PARAM_LOGIN) String login) throws UsuarioApiException {
        UsuarioResponse response = usuarioService.buscarUsuarioByLogin(login);
        return ResponseEntity.ok(response);
    }

    private void checkRequest(UsuarioVo vo) throws UsuarioApiException {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(vo, UsuarioVo.class.getName());
        ValidationUtils.invokeValidator(validator, vo, bindingResult);
        if (bindingResult.hasErrors()) {
            log.warn(LOG_REQUEST_INVALIDA);
            throw new UsuarioApiException(LOG_REQUEST_INVALIDA, bindingResult);
        }
    }

}
