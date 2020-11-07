package com.ufma.portifolium.utils;

import com.ufma.portifolium.model.entities.Professor;

public class ProfessorFactory {
    public static Professor buildProfessor() {
        Professor professor = Professor.builder().nome("Testador")
                                    .codigo("01234567")
                                    .build();
        return professor;
    }
}
