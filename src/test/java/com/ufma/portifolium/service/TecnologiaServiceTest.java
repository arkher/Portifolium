package com.ufma.portifolium.service;

import com.ufma.portifolium.model.entities.Tecnologia;
import com.ufma.portifolium.model.exceptions.TecnologiaInvalidaException;
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
public class TecnologiaServiceTest {

  TecnologiaService tecnologiaService;
  TecnologiaRepository tecnologiaRepository;

  @Autowired
  public TecnologiaServiceTest(TecnologiaService tecnologiaService, TecnologiaRepository tecnologiaRepository) {
    this.tecnologiaService = tecnologiaService;
    this.tecnologiaRepository = tecnologiaRepository;
  }

  @Test
  public void deveSalvarTecnologia() {
    Tecnologia tecnologia = TecnologiaFactory.buildTecnologia();

    Tecnologia salvo = tecnologiaService.salvar(tecnologia);

    Assertions.assertNotNull(salvo);
    Assertions.assertNotNull(salvo.getId());

    tecnologiaRepository.delete(salvo);
  }

  @Test
  public void deveGerarErroAoTentarSalvarSemDescricao() {
    Tecnologia tecnologia = Tecnologia.builder().build();

    Assertions.assertThrows(TecnologiaInvalidaException.class, () -> tecnologiaService.salvar(tecnologia),
        "Uma descrição deve ser informada.");
  }

  @Test
  public void deveGerarErroAoTentarSalvarTecnologiaJaCadastrada() {
    Tecnologia tecnologia = Tecnologia.builder().descricao("uma descrição qualquer.").build();

    Tecnologia salvo = tecnologiaRepository.save(tecnologia);

    Assertions.assertThrows(TecnologiaInvalidaException.class, () -> tecnologiaService.salvar(salvo),
        "A tecnologia já foi cadastrada.");

    tecnologiaRepository.delete(salvo);
  }

}