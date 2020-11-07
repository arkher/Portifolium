package com.ufma.portifolium.utils;

import java.time.LocalDate;

import com.ufma.portifolium.model.entities.Projeto;

public class ProjetoFactory {
    public static Projeto buildProjeto() {
        LocalDate data = LocalDate.now();

        Projeto projeto = Projeto.builder().alteradoEm(data)
                                            .aluno(AlunoFactory.buildALuno())
                                            .dataFim(data)
                                            .dataInicio(data)
                                            .descricao("Uma descrição qualquer")
                                            .validado(true)
                                            .build();
        return projeto;
    }
}
