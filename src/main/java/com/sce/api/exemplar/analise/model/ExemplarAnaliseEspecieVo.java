package com.sce.api.exemplar.analise.model;

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
public class ExemplarAnaliseEspecieVo {

    private Long exemplarId;
    private Long especieId;
    private Long analiseId;
    private int quantidade;
}
