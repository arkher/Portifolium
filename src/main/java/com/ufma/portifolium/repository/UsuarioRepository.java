package com.ufma.portifolium.repository;
import com.ufma.portifolium.model.entities.Usuario;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
    Optional<Usuario> findByCodigoAcesso(String codigoAcesso);
    Boolean existsByCodigoAcesso(String codigoAcesso);
}
