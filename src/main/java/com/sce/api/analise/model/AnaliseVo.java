package com.sce.api.analise.model;

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
public class AnaliseVo {

    private long dataEntrada;
    private long dataConclusao;
    private String laboratorio;
    private String laboratorista;

}
