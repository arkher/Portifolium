package com.ufma.portifolium.repository;

import com.ufma.portifolium.entities.Projeto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository <Projeto, Long>{
    
}
