package com.sce.api.usuario.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioResponse {

    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String tipo;
    private String login;
}
