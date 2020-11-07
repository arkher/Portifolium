package com.ufma.portifolium.model.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Aluno
 */

@Entity
@Table(name="aluno",schema="public")

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="matricula")
    private String matricula;

    @Column(name="nome")
    private String nome;

}