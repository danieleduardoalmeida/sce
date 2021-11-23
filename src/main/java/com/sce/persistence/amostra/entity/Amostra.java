package com.sce.persistence.amostra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sce.persistence.analise.entity.Analise;
import com.sce.persistence.deposito.entity.Deposito;
import com.sce.persistence.vistoria.entity.Vistoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "amostra")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Amostra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 10, nullable = false)
	private String tipo;

	@Column
	private int quantidadeTubitos;

	@ManyToOne
	@JoinColumn(name = "analise_id_fk", nullable = false)
	private Analise analise;

	@ManyToOne
	@JoinColumn(name = "deposito_id_fk", nullable = false)
	private Deposito deposito;
	
	@ManyToOne
    @JoinColumn(name = "vistoria_id_fk", nullable = false)
    private Vistoria vistoria;
}
