package com.ufma.portifolium.entities;

import java.time.LocalDate;

import javax.persistence.*;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Usuario
 */

@Entity
@Table(name="usuario",schema="public")

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_tipo")
    private TipoUsuario tipoUsuario;

    @Column(name="codigo_acesso", unique = true, nullable = false)
    private String codigoAcesso;

    @Column(name="hash_senha")
    private String senha;

    @Column(name="descricao")
    private String descricao;

    @Column(name = "criado_em")   
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate criadoEm;

}