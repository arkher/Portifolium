package com.ufma.portifolium.repository;

import com.ufma.portifolium.model.entities.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository <Aluno, Long>{
    
}
