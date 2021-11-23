package com.sce.persistence.analise.especie.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.sce.persistence.analise.entity.Analise;
import com.sce.persistence.especie.entity.Especie;

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
public class AnaliseEspecieId implements Serializable {

	private static final long serialVersionUID = 1504161552670967690L;
	
	@ManyToOne
    private Analise analise;

    @ManyToOne
    private Especie especie;
}
