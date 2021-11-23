package com.sce.api.deposito.vistoria.model;

import com.sce.api.deposito.model.DepositoResponse;
import com.sce.api.vistoria.vo.VistoriaResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepositoVistoriaResponse {

    private DepositoResponse deposito;
    private VistoriaResponse vistoria;
}
