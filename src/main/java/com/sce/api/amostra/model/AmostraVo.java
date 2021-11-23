package com.sce.api.amostra.model;

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
public class AmostraVo {

    private long analiseId;
    private long vistoriaId;
    private long depositoId;
    private String tipo;
    private int quantidadeTubitos;

}
