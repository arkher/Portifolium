package com.ufma.portifolium.model;

import java.util.Optional;

import com.ufma.portifolium.entities.Aluno;
import com.ufma.portifolium.repository.AlunoRepository;
import com.ufma.portifolium.utils.AlunoFactory;

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
    public void deveSalvarAluno() {
        Aluno aluno = AlunoFactory.buildALuno();

        Aluno salvo = repository.save(aluno);

        Assertions.assertNotNull(salvo);
        Assertions.assertNotNull(salvo.getId());
        Assertions.assertEquals(aluno.getNome(), salvo.getNome());
        Assertions.assertEquals(aluno.getMatricula(), salvo.getMatricula());
        repository.delete(salvo);
    }

    @Test
    public void deveRemoverAluno() {
        Aluno aluno = AlunoFactory.buildALuno();

        Aluno salvo = repository.save(aluno);
        Long id = salvo.getId();
        repository.deleteById(id);

        Optional<Aluno> temp = repository.findById(id);
        Assertions.assertFalse(temp.isPresent());
    }
}
