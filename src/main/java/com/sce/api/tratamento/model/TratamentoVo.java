package com.sce.api.tratamento.model;

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
public class TratamentoVo {
    
    private Long depositoId;
    private Long vistoriaId;
    private int quantidadeDepositosEliminados;
    private int quantidadeDepositosLarvicida1;
    private int quantidadeDepositosLarvicida2;
    private String tipoLarvicida1;
    private int quantidadeLarvicida1;
    private String tipoLarvicida2;
    private int quantidadeLarvicida2;
    private String tipoAdulticida;
    private int quantidadeCargasAdulticida;

}
