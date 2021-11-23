package com.sce.api.deposito.vistoria.model;

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
public class DepositoVistoriaVo {

    private Long depositoId;
    private Long vistoriaId;
}
