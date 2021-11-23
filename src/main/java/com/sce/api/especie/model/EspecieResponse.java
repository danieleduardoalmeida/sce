package com.sce.api.especie.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EspecieResponse {

    private long id;
    private String nome;
}
