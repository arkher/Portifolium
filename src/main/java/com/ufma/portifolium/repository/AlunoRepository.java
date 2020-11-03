package com.ufma.portifolium.repository;

import java.util.Optional;

import com.ufma.portifolium.entities.Aluno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository <Aluno, Long>{
    Boolean existsByMatricula(String matricula);
    Optional<Aluno> findByMatricula(String matricula);
}
