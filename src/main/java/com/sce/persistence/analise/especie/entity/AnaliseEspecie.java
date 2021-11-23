package com.sce.persistence.analise.especie.entity;

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

@Table(name = "analise_especie")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AssociationOverrides({
    @AssociationOverride(name = "id.analise", joinColumns = @JoinColumn(name = "analise_id_fk")),
    @AssociationOverride(name = "id.especie", joinColumns = @JoinColumn(name = "especie_id_fk"))
})
public class AnaliseEspecie {

    @EmbeddedId
    private AnaliseEspecieId id;
}
