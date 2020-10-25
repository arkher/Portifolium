package com.ufma.portifolium.model;

import com.ufma.portifolium.entities.Aluno;
import com.ufma.portifolium.repository.AlunoRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AlunoRepositoryTest {
    @Autowired
    AlunoRepository repository;

    @Test
    public void deveSalvarAluno(){
        //cenario
        Aluno aluno = Aluno.builder().nome("Testador")
                                    .matricula("01234567")
                                    .build();

        //acao
        Aluno salvo = repository.save(aluno);

        //verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertNotNull(salvo.getId());
        Assertions.assertEquals(aluno.getNome(), salvo.getNome());
        Assertions.assertEquals(aluno.getMatricula(), salvo.getMatricula());
    }

    // adicionar teste de remoção
}
