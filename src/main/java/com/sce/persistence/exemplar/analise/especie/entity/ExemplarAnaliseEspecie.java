package com.sce.persistence.exemplar.analise.especie.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "exemplar_analise_especie")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AssociationOverrides({
    @AssociationOverride(name = "id.exemplar", joinColumns = @JoinColumn(name = "exemplar_id_fk")),
    @AssociationOverride(name = "id.analise", joinColumns = @JoinColumn(name = "analise_id_fk")),
    @AssociationOverride(name = "id.especie", joinColumns = @JoinColumn(name = "especie_id_fk"))
})
public class ExemplarAnaliseEspecie {

    @EmbeddedId
    private ExemplarAnaliseEspecieId id;
    
    @Column
    private int quantidade;
}
