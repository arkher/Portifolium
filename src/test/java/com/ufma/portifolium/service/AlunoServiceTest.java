package com.ufma.portifolium.service;

import com.ufma.portifolium.model.entities.Aluno;
import com.ufma.portifolium.repository.AlunoRepository;
import com.ufma.portifolium.model.exceptions.AlunoInvalidoException;
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
public class AlunoServiceTest {

    AlunoService alunoService;

    AlunoRepository alunoRepository;

    @Autowired
    public AlunoServiceTest(AlunoService alunoService, AlunoRepository alunoRepository) {
        this.alunoService = alunoService;
        this.alunoRepository = alunoRepository;
    }

    @Test
    public void deveSalvarAluno() {
        Aluno aluno = AlunoFactory.buildALuno();

        Aluno salvo = alunoService.salvar(aluno);

        Assertions.assertNotNull(salvo);
        Assertions.assertNotNull(salvo.getId());

        alunoRepository.delete(salvo);
    }

    @Test
    public void deveGerarErroAoTentarSalvarSemNome() {
        Aluno aluno = Aluno.builder().matricula("12345678").build();

        Assertions.assertThrows(AlunoInvalidoException.class, () -> alunoService.salvar(aluno),
                "Um nome válido deve ser informado.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarSemMatricula() {
        Aluno aluno = Aluno.builder().nome("Teste").build();

        Assertions.assertThrows(AlunoInvalidoException.class, () -> alunoService.salvar(aluno),
                "Uma matrícula válida deve ser informada.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarMatriculaInvalida() {
        Aluno aluno = Aluno.builder().nome("Teste").matricula("asdasdasd").build();

        Assertions.assertThrows(AlunoInvalidoException.class, () -> alunoService.salvar(aluno),
                "Uma matrícula válida deve ser informada (Campo somente numérico).");
    }

    @Test
    public void deveGerarErroAoTentarSalvarAlunoJaCadastrado() {
        Aluno aluno = AlunoFactory.buildALuno();

        Aluno salvo = alunoRepository.save(aluno);

        Assertions.assertThrows(AlunoInvalidoException.class, () -> alunoService.salvar(salvo), "Aluno já cadastrado.");

        alunoRepository.delete(salvo);
    }

}
