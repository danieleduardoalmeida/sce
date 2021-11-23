package com.sce.api.deposito.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class DepositoResponse {

    private long id;
    private String nome;
    private String codigo;
    
}
