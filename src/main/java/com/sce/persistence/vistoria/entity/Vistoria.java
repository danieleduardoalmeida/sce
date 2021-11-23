package com.sce.persistence.vistoria.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sce.persistence.imovel.entity.Imovel;
import com.sce.persistence.usuario.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "vistoria")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vistoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean concluida;

    @Column
    private boolean imovelFechado;

    @Column
    private boolean recusada;

    @Column(columnDefinition = "timestamp", nullable = false)
    private LocalDateTime dataVistoria;

    @Column(length = 4, nullable = false)
    private String codigoAtividade;

    @Column(length = 11, nullable = false)
    private String tipoVisita;

    @Column
    private int tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id_fk", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "imovel_id_fk", nullable = false)
    private Imovel imovel;

    public enum TipoVistoria {

        SEDE(1, "Sede"),
        OUTROS(2, "Outros");

        private int codigo;
        private String tipo;

        private TipoVistoria(int codigo, String tipo) {
            this.codigo = codigo;
            this.tipo = tipo;
        }

        public String getTipo() {
            return tipo;
        }

        public int getCodigo() {
            return codigo;
        }

    }

    public enum TipoVisita {

        NORMAL("Normal"),
        RECUPERAÇÃO("Recuperação");

        private String tipoVisita;

        private TipoVisita(String tipoVisita) {
            this.tipoVisita = tipoVisita;
        }

        public String getTipoVisita() {
            return tipoVisita;
        }

    }

    public enum Atividade {

        LEVANTAMENTO_INDICE("1-LI", "Levantamento de índice"),
        LEVANTAMENTO_INDICE_TRATAMENTO("2-LI+T", "Levantamento de índice + Tratamento"),
        PONTO_ESTRATEGICO("3-PE", "Ponto Estratégico"),
        TRATAMENTO("4-T", "Tratamento"),
        DELIMITACAO_FOCO("5-DF", "Delimitação de foco"),
        PESQUISA_VETORIAL_ESPECIAL("6-PVE", "Pesquisa Vetorial Especial");

        private String codigo;
        private String descrição;

        private Atividade(String codigo, String descrição) {
            this.codigo = codigo;
            this.descrição = descrição;
        }

        public String getCodigo() {
            return codigo;
        }

        public String getDescrição() {
            return descrição;
        }

    }
}