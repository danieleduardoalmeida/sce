package com.sce.api.usuario.service;

import static com.sce.persistence.usuario.entity.Usuario.TipoUsuario.ADMINISTRADOR;
import static com.sce.persistence.usuario.entity.Usuario.TipoUsuario.AGENTE_EPIDEMIOLOGICO;
import static com.sce.persistence.usuario.entity.Usuario.TipoUsuario.SECRETARIA_SAUDE;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sce.api.usuario.model.UsuarioVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UsuarioVo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.debug("Validating the Usuario request.");
        UsuarioVo request = (UsuarioVo) target;

        if (validarTipoUsuario(request.getTipo())) {
            errors.rejectValue("tipo",
                    "Tipo do usuário inválido");
        }
    }

    private boolean validarTipoUsuario(String tipoUsuario) {
        return !tipoUsuario.equalsIgnoreCase(ADMINISTRADOR.name()) && !tipoUsuario.equalsIgnoreCase(AGENTE_EPIDEMIOLOGICO.name()) && !tipoUsuario.equalsIgnoreCase(SECRETARIA_SAUDE.name());
    }
}