package com.sce.api.vistoria.service;

import static com.sce.persistence.vistoria.entity.Vistoria.Atividade.DELIMITACAO_FOCO;
import static com.sce.persistence.vistoria.entity.Vistoria.Atividade.LEVANTAMENTO_INDICE;
import static com.sce.persistence.vistoria.entity.Vistoria.Atividade.LEVANTAMENTO_INDICE_TRATAMENTO;
import static com.sce.persistence.vistoria.entity.Vistoria.Atividade.PESQUISA_VETORIAL_ESPECIAL;
import static com.sce.persistence.vistoria.entity.Vistoria.Atividade.PONTO_ESTRATEGICO;
import static com.sce.persistence.vistoria.entity.Vistoria.Atividade.TRATAMENTO;
import static com.sce.persistence.vistoria.entity.Vistoria.TipoVisita.NORMAL;
import static com.sce.persistence.vistoria.entity.Vistoria.TipoVisita.RECUPERAÇÃO;
import static com.sce.persistence.vistoria.entity.Vistoria.TipoVistoria.OUTROS;
import static com.sce.persistence.vistoria.entity.Vistoria.TipoVistoria.SEDE;
import static java.time.temporal.ChronoUnit.DAYS;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sce.api.vistoria.vo.VistoriaVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VistoriaRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return VistoriaVo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.debug("Validating the Vistoria request.");
        VistoriaVo request = (VistoriaVo) target;

        if (validarAtividade(request.getCodigoAtividade())) {
            errors.rejectValue("codigoAtividade",
                    "Código da atividade inválido");
        }

        if (validarTipo(request.getTipo())) {
            errors.rejectValue("tipo",
                    "Tipo inválido");
        }

        if (validarTipoVisita(request.getTipoVisita())) {
            errors.rejectValue("tipoVisita",
                    "Tipo visita inválido");
        }
        
        if (validarDataVistoria(Instant.ofEpochMilli(request.getDataVistoria()))) {
            errors.rejectValue("dataVistoria",
                    "Data visita inválida");
        }

    }

    private boolean validarTipo(int tipo) {
        return tipo != SEDE.getCodigo() && tipo != OUTROS.getCodigo();
    }

    private boolean validarTipoVisita(String tipoVisita) {
        return !tipoVisita.equalsIgnoreCase(NORMAL.getTipoVisita()) && !tipoVisita.equalsIgnoreCase(RECUPERAÇÃO.getTipoVisita());
    }
    
    private boolean validarDataVistoria(Instant dataVistoria) {
        Instant dataAtual = Instant.now().truncatedTo(DAYS);
        return dataVistoria.isAfter(dataAtual) || dataVistoria.isBefore(dataAtual);
    }

    private boolean validarAtividade(String codigoAtividade) {
        return !codigoAtividade.equalsIgnoreCase(LEVANTAMENTO_INDICE.getCodigo()) &&
                !codigoAtividade.equalsIgnoreCase(LEVANTAMENTO_INDICE_TRATAMENTO.getCodigo()) &&
                !codigoAtividade.equalsIgnoreCase(PONTO_ESTRATEGICO.getCodigo()) &&
                !codigoAtividade.equalsIgnoreCase(TRATAMENTO.getCodigo()) &&
                !codigoAtividade.equalsIgnoreCase(DELIMITACAO_FOCO.getCodigo()) &&
                !codigoAtividade.equalsIgnoreCase(PESQUISA_VETORIAL_ESPECIAL.getCodigo());
    }
}