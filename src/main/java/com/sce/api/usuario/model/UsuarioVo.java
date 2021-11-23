package com.sce.api.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioVo {

    private String nome;
    private String email;
    private String telefone;
    private String tipo;
    private String login;
    private String senha;
}
