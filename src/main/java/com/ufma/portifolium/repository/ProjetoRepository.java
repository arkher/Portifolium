package com.ufma.portifolium.repository;

import java.util.List;

import com.ufma.portifolium.model.entities.Aluno;
import com.ufma.portifolium.model.entities.Projeto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository <Projeto, Long>{
    List<Projeto> findByAluno(Aluno aluno);
    List<Projeto> findByValidado(Boolean validado);
}
