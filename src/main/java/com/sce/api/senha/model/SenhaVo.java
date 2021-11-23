package com.sce.api.senha.model;

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
public class SenhaVo {
    private String usuario;
    private String senhaAntiga;
    private String senhaNova;
}
