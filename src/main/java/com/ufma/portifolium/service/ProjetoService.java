package com.ufma.portifolium.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ufma.portifolium.model.entities.Aluno;
import com.ufma.portifolium.model.entities.Projeto;
import com.ufma.portifolium.model.entities.Tecnologia;
import com.ufma.portifolium.model.exceptions.ProjetoInvalidoException;
import com.ufma.portifolium.repository.AlunoRepository;
import com.ufma.portifolium.repository.ProjetoRepository;
import com.ufma.portifolium.repository.TecnologiaRepository;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjetoService {
  ProjetoRepository projetoRepository;
  AlunoRepository alunoRepository;
  TecnologiaRepository tecnologiaRepository;

  @Autowired
  public ProjetoService(ProjetoRepository projetoRepository, 
                        AlunoRepository alunoRepository,
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
    return projetoRepository.save(projeto);
  }

  private void verificarProjeto(Projeto projeto) {
    if (projeto == null)
      throw new ProjetoInvalidoException("Um Projeto válido deve ser informado.");
    if (projeto.getValidado() == null)
      throw new ProjetoInvalidoException("O campo validado deve ser preenchido.");
    if (projeto.getAluno() == null)
      throw new ProjetoInvalidoException("O campo validado deve ser preenchido.");
    if (projeto.getDataInicio() == null)
      throw new ProjetoInvalidoException("O campo dataInicio deve ser preenchido.");
    if (projeto.getDataFim() == null)
      throw new ProjetoInvalidoException("O campo dataFim deve ser preenchido.");
    if (projeto.getDescricao() == null || projeto.getDescricao().equals(""))
      throw new ProjetoInvalidoException("O campo descricao deve ser preenchido.");
    if (projeto.getTecnologias() == null || projeto.getTecnologias().isEmpty())
      throw new ProjetoInvalidoException("O campo tecnologias deve ser preenchido.");
    verificaTecnologias(projeto);
  }

  private void verificaTecnologias(Projeto projeto) {
    for(Tecnologia t: projeto.getTecnologias()){
      Optional<Tecnologia> tOptional = tecnologiaRepository.findByDescricao(t.getDescricao());
      if(!tOptional.isPresent()) 
        tecnologiaRepository.save(t);
    }
  }

  @Transactional
  public void remover(Long id) {
    Optional<Projeto> projeto = projetoRepository.findById(id);
    if (projeto.isPresent())
      projetoRepository.delete(projeto.get());
  }

}