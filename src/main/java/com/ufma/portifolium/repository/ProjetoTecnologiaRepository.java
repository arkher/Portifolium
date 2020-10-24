package com.ufma.portifolium.repository;

import com.ufma.portifolium.entities.ProjetoTecnologia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoTecnologiaRepository extends JpaRepository <ProjetoTecnologia, Long>{
    
}
