package com.ufma.portifolium.utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.ufma.portifolium.model.entities.Projeto;
import com.ufma.portifolium.model.entities.Tecnologia;

public class ProjetoFactory {
    public static Projeto buildProjeto() {
        LocalDate data = LocalDate.now();
        List<Tecnologia> tecnologias = Arrays.asList(TecnologiaFactory.buildTecnologia());

        Projeto projeto = Projeto.builder().alteradoEm(data)
                                            .aluno(AlunoFactory.buildALuno())
                                            .dataFim(data)
                                            .dataInicio(data)
                                            .descricao("Uma descrição qualquer")
                                            .validado(true)
                                            .tecnologias(tecnologias)
                                            .build();
        return projeto;
    }
}
