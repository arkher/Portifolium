package com.ufma.portifolium.repository;

import com.ufma.portifolium.entities.Aluno;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository <Aluno, Long>{
    
}
