package com.ufma.portifolium.repository;

import com.ufma.portifolium.entities.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
    
}
