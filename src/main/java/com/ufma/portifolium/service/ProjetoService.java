package com.ufma.portifolium.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ufma.portifolium.model.dto.ProjetoValidacaoDTO;
import com.ufma.portifolium.model.entities.Aluno;
import com.ufma.portifolium.model.entities.Projeto;
import com.ufma.portifolium.model.entities.Tecnologia;
import com.ufma.portifolium.model.exceptions.ProjetoInvalidoException;
import com.ufma.portifolium.repository.AlunoRepository;
import com.ufma.portifolium.repository.ProjetoRepository;
import com.ufma.portifolium.repository.TecnologiaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjetoService {
  ProjetoRepository projetoRepository;
  AlunoRepository alunoRepository;
  TecnologiaRepository tecnologiaRepository;

  @Autowired
  public ProjetoService(ProjetoRepository projetoRepository, AlunoRepository alunoRepository,
      TecnologiaRepository tecnologiaRepository) {
    this.projetoRepository = projetoRepository;
    this.alunoRepository = alunoRepository;
    this.tecnologiaRepository = tecnologiaRepository;
  }

  public List<Projeto> recuperarProjetos() {
    return projetoRepository.findAll();
  }

  public List<Projeto> buscar(Projeto filtro) {
    Example<Projeto> example = Example.of(filtro,
        ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
    return projetoRepository.findAll(example);
  }

  public List<Projeto> recuperarPorAluno(Long idAluno) {
    Optional<Aluno> aluno = alunoRepository.findById(idAluno);
    if (aluno.isPresent()) {
      return projetoRepository.findByAluno(aluno.get());
    }
    return Collections.emptyList();
  }

  public List<Projeto> recuperarValidados() {
    return projetoRepository.findByValidado(true);
  }

  @Transactional
  public Projeto salvar(Projeto projeto) {
    verificarProjeto(projeto);
    Optional<Aluno> aluno = alunoRepository.findById(projeto.getAluno().getId());
    if(aluno.isPresent()) projeto.setAluno(aluno.get());
    return projetoRepository.save(projeto);
  }

  @Transactional
  public Projeto validar(ProjetoValidacaoDTO validado, Long id){
    Optional<Projeto> projeto = projetoRepository.findById(id);
    if(projeto.isPresent()){
      projeto.get().setValidado(validado.getValidado());
      return projetoRepository.save(projeto.get());
    } 
    return null;
  }

  private void verificarProjeto(Projeto projeto) {
    if (projeto == null)
      throw new ProjetoInvalidoException("Um Projeto válido deve ser informado.");
    if (projeto.getAluno() == null)
      throw new ProjetoInvalidoException("O campo aluno deve ser preenchido.");
    if (projeto.getDescricao() == null || projeto.getDescricao().equals(""))
      throw new ProjetoInvalidoException("O campo descricao deve ser preenchido.");
    if (projeto.getTecnologias() == null || projeto.getTecnologias().isEmpty())
      throw new ProjetoInvalidoException("O campo tecnologias deve ser preenchido.");
    verificaTecnologias(projeto);
  }

  private void verificaTecnologias(Projeto projeto) {
    List<Tecnologia> tecnologias = new ArrayList<>();
    for (Tecnologia t : projeto.getTecnologias()) {
      Optional<Tecnologia> tOptional = tecnologiaRepository.findByDescricao(t.getDescricao());
      if (!tOptional.isPresent()) {
        tecnologias.add(tecnologiaRepository.save(t));
      }
      else {
        tecnologias.add(tOptional.get());
      }
    }
    projeto.setTecnologias(tecnologias);
  }

  @Transactional
  public void remover(Long id) {
    Optional<Projeto> projeto = projetoRepository.findById(id);
    if (projeto.isPresent())
      projetoRepository.delete(projeto.get());
  }

}