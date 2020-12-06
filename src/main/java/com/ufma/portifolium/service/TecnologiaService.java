package com.ufma.portifolium.service;

import java.util.List;
import java.util.Optional;

import com.ufma.portifolium.model.entities.Tecnologia;
import com.ufma.portifolium.model.exceptions.TecnologiaInvalidaException;
import com.ufma.portifolium.repository.TecnologiaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TecnologiaService {
  
  TecnologiaRepository tecnologiaRepository;

  @Autowired
  public TecnologiaService(TecnologiaRepository tecnologiaRepository) { this.tecnologiaRepository = tecnologiaRepository; }

  public List<Tecnologia> recuperarTecnologias(){ return tecnologiaRepository.findAll(); }

  public Tecnologia recuperarPorDescricao(String descricao) {
    Optional<Tecnologia> tecnologia = tecnologiaRepository.findByDescricao(descricao);
    if (tecnologia.isPresent()) {
      return tecnologia.get();
    }
    return null;
  }

  @Transactional
  public Tecnologia salvar(Tecnologia tecnologia) {
    verificaTecnologia(tecnologia);
    return tecnologiaRepository.save(tecnologia);
  }

  public void remover(Tecnologia tecnologia) {
    verificaTecnologia(tecnologia);
    tecnologiaRepository.delete(tecnologia);
  }

  public void remover(Long id) {
    Optional<Tecnologia> tecnologia = tecnologiaRepository.findById(id);
    if(tecnologia.isPresent()) tecnologiaRepository.delete(tecnologia.get());
  }

  private void verificaTecnologia(Tecnologia tecnologia) {
    if(tecnologia.getDescricao()==null || tecnologia.getDescricao().equals(""))
      throw new TecnologiaInvalidaException("Uma descrição deve ser informada.");
    
    boolean tecnologiaJaCadastrada = tecnologiaRepository.existsByDescricao(tecnologia.getDescricao());
    if(tecnologiaJaCadastrada) 
      throw new TecnologiaInvalidaException("A tecnologia já foi cadastrada."); 
  }

}