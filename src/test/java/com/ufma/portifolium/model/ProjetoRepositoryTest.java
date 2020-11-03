package com.ufma.portifolium.model;

import java.util.Optional;

import com.ufma.portifolium.entities.Projeto;
import com.ufma.portifolium.repository.ProjetoRepository;
import com.ufma.portifolium.utils.ProjetoFactory;

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
public class ProjetoRepositoryTest {

    @Autowired
    ProjetoRepository repository;

    @Test
    public void deveSalvarProjeto(){
        Projeto projeto = ProjetoFactory.buildProjeto();

        Projeto salvo = repository.save(projeto);
        
        Assertions.assertNotNull(salvo);
        Assertions.assertNotNull(salvo.getAluno());
        Assertions.assertNotNull(salvo.getId());
        Assertions.assertEquals(projeto, salvo);
        repository.delete(salvo);

    }

    @Test
    public void deveRemoverProjeto() {
        Projeto projeto = ProjetoFactory.buildProjeto();

        Projeto salvo = repository.save(projeto);    
        Long idProjetoSalvo = salvo.getId();
        repository.deleteById(idProjetoSalvo);

        Optional<Projeto> temp = repository.findById(idProjetoSalvo);
        Assertions.assertFalse(temp.isPresent());

    }

    // Fazer teste para verificar se matrícula são apenas números
    // Fazer teste para verificar se foi validado
}
