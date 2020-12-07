package com.ufma.portifolium.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ufma.portifolium.model.entities.Aluno;
import com.ufma.portifolium.model.entities.Projeto;
import com.ufma.portifolium.model.entities.Tecnologia;
import com.ufma.portifolium.model.exceptions.ProjetoInvalidoException;
import com.ufma.portifolium.repository.AlunoRepository;
import com.ufma.portifolium.repository.ProjetoRepository;
import com.ufma.portifolium.repository.TecnologiaRepository;
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
public class ProjetoServiceTest {
  @Autowired
  ProjetoService projetoService;

  @Autowired
  ProjetoRepository projetoRepository;

  @Autowired
  TecnologiaRepository tecnologiaRepository;

  @Autowired
  AlunoRepository alunoRepository;

  @Test
  public void deveSalvarProjeto() {
    Projeto projeto = ProjetoFactory.buildProjeto();
    Aluno aluno = Aluno.builder().nome("RAIMUNDAO").matricula("123456").build();
    List<Tecnologia> tecnologias = Arrays.asList(Tecnologia.builder()
                        .descricao("descricao").build());
    
    projeto.setAluno(alunoRepository.save(aluno));
    tecnologias.stream().forEach((x)->{x=tecnologiaRepository.save(x);});
    projeto.setTecnologias(tecnologias);
    
    Projeto salvo = projetoService.salvar(projeto);
    Tecnologia tProjeto = salvo.getTecnologias().get(0);

    Assertions.assertNotNull(salvo);
    Assertions.assertNotNull(salvo.getId());

    projetoRepository.delete(salvo);
    tecnologiaRepository.delete(tProjeto);
  }

  @Test
  public void deveGerarErroAoTentarSalvarProjetoInvalido() {
    Assertions.assertThrows(ProjetoInvalidoException.class, () -> projetoService.salvar(null),
        "Um Projeto válido deve ser informado.");
  }

  @Test
  public void deveGerarErroAoTentarSalvarSemAluno() {
    Projeto projeto = ProjetoFactory.buildProjeto();
    projeto.setAluno(null);

    Assertions.assertThrows(ProjetoInvalidoException.class, () -> projetoService.salvar(projeto),
        "O campo aluno deve ser preenchido.");
  }

  @Test
  public void deveGerarErroAoTentarSalvarSemDescricao() {
    Projeto projeto = ProjetoFactory.buildProjeto();
    projeto.setDescricao("");

    Assertions.assertThrows(ProjetoInvalidoException.class, () -> projetoService.salvar(projeto),
        "O campo descricao deve ser preenchido.");
  }

  @Test
  public void deveGerarErroAoTentarSalvarSemValidado() {
    Projeto projeto = ProjetoFactory.buildProjeto();
    projeto.setValidado(null);

    Assertions.assertThrows(ProjetoInvalidoException.class, () -> projetoService.salvar(projeto),
        "O campo validado deve ser preenchido.");
  }

  @Test
  public void deveGerarErroAoTentarSalvarSemDataInicio() {
    Projeto projeto = ProjetoFactory.buildProjeto();
    projeto.setDataInicio(null);

    Assertions.assertThrows(ProjetoInvalidoException.class, () -> projetoService.salvar(projeto),
        "O campo dataInicio deve ser preenchido.");
  }

  @Test
  public void deveGerarErroAoTentarSalvarSemDataFim() {
    Projeto projeto = ProjetoFactory.buildProjeto();
    projeto.setDataFim(null);

    Assertions.assertThrows(ProjetoInvalidoException.class, () -> projetoService.salvar(projeto),
        "O campo dataFim deve ser preenchido.");
  }

  @Test
  public void deveGerarErroAoTentarSalvarSemTecnologias() {
    Projeto projeto = ProjetoFactory.buildProjeto();
    projeto.setTecnologias(null);

    Assertions.assertThrows(ProjetoInvalidoException.class, () -> projetoService.salvar(projeto),
        "O campo tecnologias deve ser preenchido.");
  }

  @Test
  public void deveSalvarNovaTecnologiaSeNaoExistir() {

    Projeto projeto = ProjetoFactory.buildProjeto();
    Aluno aluno = Aluno.builder().nome("RAIMUNDAO").matricula("123456").build();
    List<Tecnologia> tecnologias = Arrays.asList(Tecnologia.builder()
                        .descricao("descricao").build());
    
    projeto.setAluno(alunoRepository.save(aluno));
    tecnologias.stream().forEach((x)->{x=tecnologiaRepository.save(x);});
    projeto.setTecnologias(tecnologias);
    
    Projeto salvo = projetoService.salvar(projeto);
    Tecnologia tProjeto = salvo.getTecnologias().get(0);

    boolean tecnologiaFoiCriada = tecnologiaRepository.existsByDescricao(tProjeto.getDescricao());

    Assertions.assertTrue(tecnologiaFoiCriada);

    projetoRepository.delete(salvo);
    tecnologiaRepository.delete(tProjeto);
  }
}