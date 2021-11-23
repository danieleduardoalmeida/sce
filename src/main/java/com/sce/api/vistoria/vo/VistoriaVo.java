package com.sce.api.vistoria.vo;

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
public class VistoriaVo {

    private boolean concluida;
    private boolean imovelFechado;
    private boolean recusada;
    private long dataVistoria;
    private String codigoAtividade;
    private String tipoVisita;
    private int tipo;
    private long usuarioId;
    private long imovelId;
}
