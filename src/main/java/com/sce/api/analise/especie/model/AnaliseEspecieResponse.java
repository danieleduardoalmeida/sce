package com.sce.api.analise.especie.model;

import com.sce.api.analise.model.AnaliseResponse;
import com.sce.api.especie.model.EspecieResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnaliseEspecieResponse {

    private AnaliseResponse analise;
    private EspecieResponse especie;
}
