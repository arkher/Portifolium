package com.ufma.portifolium.utils;

import com.ufma.portifolium.entities.Aluno;

public class AlunoFactory {
    public static Aluno buildALuno() {
        Aluno aluno = Aluno.builder().nome("Testador")
                                    .matricula("01234567")
                                    .build();

        return aluno;
    }
}
