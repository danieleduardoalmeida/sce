package com.sce.persistence.imovel.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sce.persistence.vistoria.entity.Vistoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "imovel")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imovel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private int numero;

    @Column
    private int numeroQuarteirao;

    @Column(length = 255)
    private String rua;

    @Column(length = 100, nullable = false)
    private String bairro;

    @Column(length = 20, nullable = false)
    private String tipo;

    @Column(length = 10, nullable = false)
    private String zona;

    @Column(length = 100, nullable = false)
    private String cidade;
    
    @Column(length = 20, nullable = false)
    private String cep;

    @Column(length = 10)
    private String lado;

    @Column(length = 20)
    private String complemento;
    
    @Builder.Default
    @OneToMany(mappedBy = "imovel")
    private Set<Vistoria> vistorias = new HashSet<>();

    public enum TipoImovel {

        RESIDENCIAL("Residencial"),
        COMERCIO("Comércio"),
        TERRENO_BALDIO("Terreno Baldio"),
        PONTO_ESTRATEGICO("Ponto Estratégico"),
        OUTRO("Outro");

        private String tipo;

        private TipoImovel(String tipo) {
            this.tipo = tipo;
        }

        public String getTipo() {
            return tipo;
        }
    }

    public enum ZonaImovel {

        URBANA("Urbana"),
        RURAL("Rural");

        private String zona;

        private ZonaImovel(String zona) {
            this.zona = zona;
        }

        public String getZona() {
            return zona;
        }

    }
}
