package com.sce.persistence.tratamento.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sce.persistence.deposito.entity.Deposito;
import com.sce.persistence.vistoria.entity.Vistoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tratamento")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "deposito_id_fk", nullable = false)
    private Deposito deposito;
    
    @OneToOne
    @JoinColumn(name = "vistoria_id_fk", nullable = false)
    private Vistoria vistoria;

    @Column
    private int quantidadeDepositosEliminados;

    @Column(name="tipo_larvicida_1", length = 45)
    private String tipoLarvicida1;

    @Column(name="quantidade_larvicida_1")
    private int quantidadeLarvicida1;
    
    @Column(name="quantidade_depositos_larvicida_1")
    private int quantidadeDepositosLarvicida1;

    @Column(name="tipo_larvicida_2", length = 45)
    private String tipoLarvicida2;

    @Column(name="quantidade_larvicida_2")
    private int quantidadeLarvicida2;
    
    @Column(name="quantidade_depositos_larvicida_2")
    private int quantidadeDepositosLarvicida2;

    @Column(length = 45)
    private String tipoAdulticida;

    @Column
    private int quantidadeCargasAdulticida;
}
