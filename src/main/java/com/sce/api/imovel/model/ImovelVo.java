package com.sce.api.imovel.model;

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
public class ImovelVo {
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
