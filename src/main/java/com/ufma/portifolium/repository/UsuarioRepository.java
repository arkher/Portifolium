package com.ufma.portifolium.repository;

import java.util.Optional;

import com.ufma.portifolium.entities.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
    Optional<Usuario> findByCodigoAcesso(String codigoAcesso);
    Boolean existsByCodigoAcesso(String codigoAcesso);
}
