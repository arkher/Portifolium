package com.ufma.portifolium.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Professor
 */

@Entity
@Table(name="professor",schema="public")

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="codigo")
    private String codigo;

    @Column(name="nome")
    private String nome;

}