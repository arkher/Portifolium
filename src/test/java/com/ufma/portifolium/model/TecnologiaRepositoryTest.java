package com.ufma.portifolium.model;

import java.util.Optional;

import com.ufma.portifolium.entities.Tecnologia;
import com.ufma.portifolium.repository.TecnologiaRepository;
import com.ufma.portifolium.utils.TecnologiaFactory;

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
public class TecnologiaRepositoryTest {
    @Autowired
    TecnologiaRepository repository;

    @Test
    public void deveSalvarTecnologia() {
        Tecnologia tecnologia = TecnologiaFactory.buildTecnologia();

        Tecnologia salvo = repository.save(tecnologia);

        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(tecnologia, salvo);
        Assertions.assertNotNull(salvo.getId());
        repository.delete(salvo);
    }

    @Test
    public void deveRemoverTecnologia() {
        Tecnologia tecnologia = TecnologiaFactory.buildTecnologia();

        Tecnologia salvo = repository.save(tecnologia);
        Long id = salvo.getId();
        repository.deleteById(id);

        Optional<Tecnologia> temp = repository.findById(id);
        Assertions.assertFalse(temp.isPresent());
    }
}
