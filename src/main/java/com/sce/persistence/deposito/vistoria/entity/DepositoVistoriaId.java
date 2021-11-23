package com.sce.persistence.deposito.vistoria.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.sce.persistence.deposito.entity.Deposito;
import com.sce.persistence.vistoria.entity.Vistoria;

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
public class DepositoVistoriaId implements Serializable {

    private static final long serialVersionUID = -5259609174262773156L;

    @ManyToOne(fetch = FetchType.EAGER)
    private Deposito deposito;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vistoria vistoria;
}
