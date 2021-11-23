package com.sce.api.amostra.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AmostraResponse {

    private long id;
    private long analiseId;
    private long vistoriaId;
    private long depositoId;
    private String tipo;
    private int quantidadeTubitos;

}
