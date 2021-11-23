package com.sce.api.imovel.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImovelResponse {
    private long id;
    private int numero;
    private int numeroQuarteirao;
    private String rua;
    private String bairro;
    private String tipo;
    private String zona;
    private String cidade;
    private String lado;
    private String complemento;
    private String cep;
}
