package com.sce.persistence.usuario.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;

import com.sce.persistence.vistoria.entity.Vistoria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuario")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String nome;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(length = 50, nullable = false)
    private String tipo;

    @Column(length = 100, nullable = false)
    private String login;

    @Column(length = 50, nullable = false)
    private String senha;

    @Builder.Default
    @OneToMany(mappedBy = "usuario")
    private Set<Vistoria> vistorias = new HashSet<>();

    public enum TipoUsuario {
        ADMINISTRADOR, AGENTE_EPIDEMIOLOGICO, SECRETARIA_SAUDE;
    }

}
