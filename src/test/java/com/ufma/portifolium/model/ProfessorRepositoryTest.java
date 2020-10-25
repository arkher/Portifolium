package com.ufma.portifolium.model;

import com.ufma.portifolium.entities.Professor;
import com.ufma.portifolium.repository.ProfessorRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProfessorRepositoryTest {
    @Autowired
    ProfessorRepository repository;

    @Test
    public void deveSalvarProfessor(){
        //cenario
        Professor professor = Professor.builder().nome("Testador")
                                    .codigo("01234567")
                                    .build();

        //acao
        Professor salvo = repository.save(professor);

        //verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertNotNull(salvo.getId());
        Assertions.assertEquals(professor.getNome(), salvo.getNome());
        Assertions.assertEquals(professor.getCodigo(), salvo.getCodigo());
    }
}
