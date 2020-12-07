package com.ufma.portifolium.repository;

import java.util.Optional;

import com.ufma.portifolium.model.entities.TipoUsuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository <TipoUsuario, Long>{
    Boolean existsByDescricao(String descricao);
    Optional<TipoUsuario> findByDescricao(String descricao);
}
