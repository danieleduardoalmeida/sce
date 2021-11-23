package com.sce.api.tratamento.model;

import com.sce.api.deposito.model.DepositoResponse;
import com.sce.api.vistoria.vo.VistoriaResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TratamentoResponse {
    
    private long id;
    private int quantidadeDepositosEliminados;
    private String tipoLarvicida1;
    private int quantidadeLarvicida1;
    private int quantidadeDepositosLarvicida1;
    private String tipoLarvicida2;
    private int quantidadeLarvicida2;
    private int quantidadeDepositosLarvicida2;
    private String tipoAdulticida;
    private int quantidadeCargasAdulticida;
    private DepositoResponse deposito;
    private VistoriaResponse vistoria;
}
