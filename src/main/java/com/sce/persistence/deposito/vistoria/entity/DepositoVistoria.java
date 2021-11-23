package com.sce.persistence.deposito.vistoria.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "deposito_vistoria")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AssociationOverrides({
        @AssociationOverride(name = "id.deposito", joinColumns = @JoinColumn(name = "deposito_id_fk")),
        @AssociationOverride(name = "id.vistoria", joinColumns = @JoinColumn(name = "vistoria_id_fk"))
})
public class DepositoVistoria {

    @EmbeddedId
    private DepositoVistoriaId id;

}
