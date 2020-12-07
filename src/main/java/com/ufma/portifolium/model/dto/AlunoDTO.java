package com.ufma.portifolium.model.dto;

import java.util.List;

import com.ufma.portifolium.model.entities.Aluno;
import com.ufma.portifolium.model.entities.Tecnologia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {
    private Long id;

    private String nome;

    private String descricao;

    private List<Tecnologia> tecnologias;

    public AlunoDTO(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public AlunoDTO(Aluno aluno){
        this.id = aluno.getId();
        this.nome = aluno.getNome();
    }
}
