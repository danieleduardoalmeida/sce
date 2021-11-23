package com.sce.api.vistoria.vo;

import com.sce.api.imovel.model.ImovelResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VistoriaResponse {

    private boolean concluida;
    private boolean imovelFechado;
    private boolean recusada;
    private long dataVistoria;
    private String codigoAtividade;
    private String tipoVisita;
    private int tipo;
    private long usuarioId;
    private long id;
    private ImovelResponse imovel;
}
