package com.ufma.portifolium.entities;

import java.time.LocalDate;

import javax.persistence.*;

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
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_aluno")
    private Aluno aluno;

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

}