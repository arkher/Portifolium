package com.ufma.portifolium.model;

import java.util.Optional;

import com.ufma.portifolium.model.entities.Projeto;
import com.ufma.portifolium.model.entities.ProjetoTecnologia;
import com.ufma.portifolium.model.entities.Tecnologia;
import com.ufma.portifolium.repository.ProjetoTecnologiaRepository;
import com.ufma.portifolium.utils.ProjetoFactory;
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
public class ProjetoTecnologiaRepositoryTest {
    @Autowired
    ProjetoTecnologiaRepository repository;

    @Test
    public void deveSalvarProjetoTecnologia() {
        Projeto projeto = ProjetoFactory.buildProjeto();
        Tecnologia tecnologia = TecnologiaFactory.buildTecnologia();
        ProjetoTecnologia projetoTecnologia = ProjetoTecnologia.builder()
                                                .projeto(projeto)
                                                .tecnologia(tecnologia)
                                                .build();

        ProjetoTecnologia salvo = repository.save(projetoTecnologia);

        //verificação
        Assertions.assertNotNull(salvo);
        Assertions.assertNotNull(salvo.getId());
        Assertions.assertEquals(projetoTecnologia, salvo);
        repository.delete(salvo);
    }

    @Test
    public void deveRemoverProjetoTecnologia() {
        Projeto projeto = ProjetoFactory.buildProjeto();
        Tecnologia tecnologia = TecnologiaFactory.buildTecnologia();
        ProjetoTecnologia projetoTecnologia = ProjetoTecnologia.builder()
                                                .projeto(projeto)
                                                .tecnologia(tecnologia)
                                                .build();

        ProjetoTecnologia salvo = repository.save(projetoTecnologia);
        Long id = salvo.getId();
        repository.deleteById(id);

        Optional<ProjetoTecnologia> temp = repository.findById(id);
        Assertions.assertFalse(temp.isPresent());
    }
}
