package com.ufma.portifolium.model.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Projeto
 */

@Entity
@Table(name="projeto",schema="public")

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonIgnore
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_aluno")
    private Aluno aluno;

    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;

    @Column(name="validado")
    private Boolean validado;
    
    @Column(name = "data_inicio")   
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataInicio;

    @Column(name = "data_fim")   
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataFim;

    @Column(name = "alterado_em")   
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate alteradoEm;
    
    @ManyToMany
    @JoinTable(
        name = "projeto_tecnologia",
        joinColumns = { @JoinColumn(name = "id_projeto") },
        inverseJoinColumns = { @JoinColumn(name = "id_tecnologia") }
    )
    private List<Tecnologia> tecnologias;

}