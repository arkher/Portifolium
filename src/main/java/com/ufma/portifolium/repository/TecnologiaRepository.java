package com.ufma.portifolium.repository;

import java.util.Optional;

import com.ufma.portifolium.model.entities.Tecnologia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnologiaRepository extends JpaRepository <Tecnologia, Long>{
    Optional<Tecnologia> findByDescricao(String descricao);
}
