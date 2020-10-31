package com.ufma.portifolium.model;

import java.util.Optional;

import com.ufma.portifolium.entities.Professor;
import com.ufma.portifolium.repository.ProfessorRepository;
import com.ufma.portifolium.utils.ProfessorFactory;
import com.ufma.portifolium.utils.Utils;

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
        Professor professor = ProfessorFactory.buildProfessor();

        Professor salvo = repository.save(professor);

        Assertions.assertNotNull(salvo);
        Assertions.assertNotNull(salvo.getId());
        Assertions.assertEquals(professor.getNome(), salvo.getNome());
        Assertions.assertEquals(professor.getCodigo(), salvo.getCodigo());
        Assertions.assertTrue(Utils.isNumeric(salvo.getCodigo()));

    }

    @Test
    public void deveRemoverProfessor() {
        Professor professor = ProfessorFactory.buildProfessor();
        
        Professor salvo = repository.save(professor);
        Long id = salvo.getId();
        repository.deleteById(id);

        Optional<Professor> temp = repository.findById(id);
        Assertions.assertFalse(temp.isPresent());
    }
}
