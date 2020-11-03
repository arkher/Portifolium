package com.ufma.portifolium.service;

import com.ufma.portifolium.entities.Professor;
import com.ufma.portifolium.repository.ProfessorRepository;
import com.ufma.portifolium.service.exceptions.CadastroException;
import com.ufma.portifolium.utils.ProfessorFactory;

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
public class ProfessorServiceTest {
    
    ProfessorService professorService;

    ProfessorRepository professorRepository;

    @Autowired
    public ProfessorServiceTest(ProfessorService professorService, ProfessorRepository professorRepository){
        this.professorService = professorService;
        this.professorRepository = professorRepository;
    }

    @Test
    public void deveSalvarProfessor() {
        Professor professor = ProfessorFactory.buildProfessor();
        
        Professor salvo = professorService.salvar(professor);

        Assertions.assertNotNull(salvo);
        Assertions.assertNotNull(salvo.getId());
        
        professorRepository.delete(salvo);
    }

    @Test
    public void deveGerarErroAoTentarSalvarSemNome() {
        Professor professor = Professor.builder()
                            .codigo("12345678")                
                            .build();
        
        Assertions.assertThrows(CadastroException.class, 
                                () -> professorService.salvar(professor), "Um nome válido deve ser informado.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarSemCodigo() {
        Professor professor = Professor.builder()
                            .nome("Teste")                
                            .build();
        
        Assertions.assertThrows(CadastroException.class, 
                                () -> professorService.salvar(professor), "Um código válido deve ser informado.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarCodigoInvalido() {
        Professor professor = Professor.builder()
                            .nome("Teste")
                            .codigo("asdasdasd")                
                            .build();

        Assertions.assertThrows(CadastroException.class, 
                                () -> professorService.salvar(professor), "Um código válido deve ser informado (Campo somente numérico).");
    }

    @Test
    public void deveGerarErroAoTentarSalvarProfessorJaCadastrado() {
        Professor professor = ProfessorFactory.buildProfessor();

        Professor salvo = professorRepository.save(professor);

        Assertions.assertThrows(CadastroException.class, 
                                () -> professorService.salvar(salvo), "Professor já cadastrado.");
    }

}
