package com.sce.api.exemplar.analise.model;

import com.sce.api.analise.model.AnaliseResponse;
import com.sce.api.especie.model.EspecieResponse;
import com.sce.api.exemplar.model.ExemplarResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ExemplarAnaliseEspecieResponse {

    private long id;
    private ExemplarResponse exemplar;
    private EspecieResponse especie;
    private AnaliseResponse analise;
    private int quantidade;
}
