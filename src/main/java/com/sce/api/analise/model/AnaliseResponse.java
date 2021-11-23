package com.sce.api.analise.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnaliseResponse {

    private long id;
    private long dataEntrada;
    private long dataConclusao;
    private String laboratorio;
    private String laboratorista;

}
