package com.ufma.portifolium.repository;

import java.util.List;

import com.ufma.portifolium.model.entities.Aluno;
import com.ufma.portifolium.model.entities.Projeto;
import com.ufma.portifolium.model.entities.Tecnologia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjetoRepository extends JpaRepository <Projeto, Long>{
    List<Projeto> findByAluno(Aluno aluno);
    List<Projeto> findByValidado(Boolean validado);
    
    @Query("select p.tecnologias from Projeto p where p.aluno.id=:aluno")
    List<Tecnologia> findByIdAluno(@Param("aluno") Long idAluno);
}
