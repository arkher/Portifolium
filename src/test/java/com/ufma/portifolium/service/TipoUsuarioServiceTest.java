package com.ufma.portifolium.service;

import com.ufma.portifolium.model.entities.TipoUsuario;
import com.ufma.portifolium.model.exceptions.UsuarioInvalidoException;
import com.ufma.portifolium.repository.TipoUsuarioRepository;

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
public class TipoUsuarioServiceTest {

  TipoUsuarioService tipoUsuarioService;
  TipoUsuarioRepository tipoUsuarioRepository;

  @Autowired
  public TipoUsuarioServiceTest(TipoUsuarioService tipoUsuarioService, TipoUsuarioRepository tipoUsuarioRepository) {
    this.tipoUsuarioService = tipoUsuarioService;
    this.tipoUsuarioRepository = tipoUsuarioRepository;
  }

  @Test
  public void deveSalvarTipoUsuario() {
    TipoUsuario tipoUsuario = TipoUsuario.builder().descricao("uma descrição qualquer.").build();

    TipoUsuario salvo = tipoUsuarioService.salvar(tipoUsuario);

    Assertions.assertNotNull(salvo);
    Assertions.assertNotNull(salvo.getId());

    tipoUsuarioRepository.delete(salvo);
  }

  @Test
  public void deveGerarErroAoTentarSalvarSemDescricao() {
    TipoUsuario tipoUsuario = TipoUsuario.builder().build();

    Assertions.assertThrows(UsuarioInvalidoException.class, () -> tipoUsuarioService.salvar(tipoUsuario),
        "Uma descrição deve ser informada.");
  }

  @Test
  public void deveGerarErroAoTentarSalvarTipoUsuarioJaCadastrado() {
    TipoUsuario tipoUsuario = TipoUsuario.builder().descricao("uma descrição qualquer.").build();

    TipoUsuario salvo = tipoUsuarioRepository.save(tipoUsuario);

    Assertions.assertThrows(UsuarioInvalidoException.class, () -> tipoUsuarioService.salvar(salvo),
        "Tipo de usuario já cadastrado.");

    tipoUsuarioRepository.delete(salvo);
  }

}