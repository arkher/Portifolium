package com.ufma.portifolium.repository;
import com.ufma.portifolium.model.entities.Professor;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository <Professor, Long>{
    Optional<Professor> findByCodigo(String codigo);
    Boolean existsByCodigo(String codigo);
}
