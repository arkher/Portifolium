package com.ufma.portifolium.service;

import java.util.List;
import java.util.Optional;

import com.ufma.portifolium.model.entities.TipoUsuario;
import com.ufma.portifolium.model.exceptions.UsuarioInvalidoException;
import com.ufma.portifolium.repository.TipoUsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoUsuarioService {
  TipoUsuarioRepository tipoUsuarioRepository;

  @Autowired
  public TipoUsuarioService(TipoUsuarioRepository tipoUsuarioRepository) {
    this.tipoUsuarioRepository = tipoUsuarioRepository;
  }

  public List<TipoUsuario> recuperarUsuarios() {
    return tipoUsuarioRepository.findAll();
  }

  @Transactional
  public TipoUsuario salvar(TipoUsuario tUsuario) {
    verificarTipoUsuario(tUsuario);
    return tipoUsuarioRepository.save(tUsuario);
  }

  public void remover(TipoUsuario tUsuario) {
    verificarTipoUsuario(tUsuario);
    tipoUsuarioRepository.delete(tUsuario);
  }

  public void remover(Long id) {
    Optional<TipoUsuario> tUsuario = tipoUsuarioRepository.findById(id);
    if (tUsuario.isPresent())
      remover(tUsuario.get());
  }

  private void verificarTipoUsuario(TipoUsuario tUsuario) {
    if (tUsuario == null)
      throw new UsuarioInvalidoException("Um tipo de usuário válido deve ser informado.");
    if (tUsuario.getDescricao() == null || tUsuario.getDescricao().equals(""))
      throw new UsuarioInvalidoException("Uma descrição deve ser informada.");

    boolean tipoUsuarioJaCadastrado = tipoUsuarioRepository.existsByDescricao(tUsuario.getDescricao());
    if (tipoUsuarioJaCadastrado)
      throw new UsuarioInvalidoException("Tipo de usuario já cadastrado.");
  }

}