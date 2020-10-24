package com.ufma.portifolium.repository;

import com.ufma.portifolium.entities.Projeto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository <Projeto, Long>{
    
}
