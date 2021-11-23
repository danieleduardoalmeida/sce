package com.sce.persistence.exemplar.analise.especie.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.sce.persistence.analise.entity.Analise;
import com.sce.persistence.especie.entity.Especie;
import com.sce.persistence.exemplar.entity.Exemplar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExemplarAnaliseEspecieId implements Serializable {

    private static final long serialVersionUID = 7362574257713044232L;

    @ManyToOne
    private Exemplar exemplar;

    @ManyToOne
    private Especie especie;

    @ManyToOne
    private Analise analise;

}