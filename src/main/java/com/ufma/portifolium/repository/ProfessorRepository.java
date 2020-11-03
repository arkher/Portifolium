package com.ufma.portifolium.repository;

import java.util.Optional;

import com.ufma.portifolium.entities.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository <Professor, Long>{
    Optional<Professor> findByCodigo(String codigo);
    Boolean existsByCodigo(String codigo);
}
