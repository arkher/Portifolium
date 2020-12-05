package com.ufma.portifolium.model.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Tecnologia
 */

@Entity
@Table(name="tecnologia",schema="public")

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonIgnore
    private Long id;

    @Column(name="descricao")
    private String descricao;

    @ManyToMany(mappedBy = "tecnologias")
    private List<Projeto> projetos;

}