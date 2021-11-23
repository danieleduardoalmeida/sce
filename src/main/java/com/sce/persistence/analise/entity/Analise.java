package com.sce.persistence.analise.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sce.persistence.amostra.entity.Amostra;
import com.sce.persistence.vistoria.entity.Vistoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "analise")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Analise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "timestamp", nullable = false)
    private LocalDateTime dataEntrada;

    @Column(columnDefinition = "timestamp", nullable = false)
    private LocalDateTime dataConclusao;

    @Column(length = 255, nullable = false)
    private String laboratorio;
    
    @Column(length = 255, nullable = false)
    private String laboratorista;

    @OneToMany
    @JoinColumn(name = "vistoria_id_fk", nullable = false)
    private Vistoria vistoria;

    @Builder.Default
    @OneToMany(mappedBy = "analise")
    private Set<Amostra> amostras = new HashSet<>();
}
