package com.sce.api.imovel.service;

import static com.sce.persistence.imovel.entity.Imovel.TipoImovel.COMERCIO;
import static com.sce.persistence.imovel.entity.Imovel.TipoImovel.OUTRO;
import static com.sce.persistence.imovel.entity.Imovel.TipoImovel.PONTO_ESTRATEGICO;
import static com.sce.persistence.imovel.entity.Imovel.TipoImovel.RESIDENCIAL;
import static com.sce.persistence.imovel.entity.Imovel.TipoImovel.TERRENO_BALDIO;
import static com.sce.persistence.imovel.entity.Imovel.ZonaImovel.RURAL;
import static com.sce.persistence.imovel.entity.Imovel.ZonaImovel.URBANA;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sce.api.imovel.model.ImovelVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImovelRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ImovelVo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.debug("Validating the imóvel request.");
        ImovelVo request = (ImovelVo) target;

        if (validarTipoImovel(request.getTipo())) {
            errors.rejectValue("tipo",
                    "Tipo do imóvel inválido");
        }
        
        if (validarZonaImovel(request.getZona())) {
            errors.rejectValue("zona",
                    "Zona inválida");
        }
    }

    private boolean validarTipoImovel(String tipoImovel) {
        return !tipoImovel.equalsIgnoreCase(COMERCIO.getTipo()) 
                && !tipoImovel.equalsIgnoreCase(RESIDENCIAL.getTipo()) 
                && tipoImovel.equalsIgnoreCase(OUTRO.getTipo())
                && tipoImovel.equalsIgnoreCase(PONTO_ESTRATEGICO.getTipo())
                && tipoImovel.equalsIgnoreCase(TERRENO_BALDIO.getTipo());
    }
    
    private boolean validarZonaImovel(String zonaImovel) {
        return !zonaImovel.equalsIgnoreCase(RURAL.getZona())
                && !zonaImovel.equalsIgnoreCase(URBANA.getZona());
    }
}