package com.ufma.portifolium.repository;

import com.ufma.portifolium.model.entities.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository <Professor, Long>{
    
}
