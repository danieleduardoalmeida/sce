package com.sce.api.exemplar.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExemplarResponse {

    private long id;
    private String nome;
}
