package com.ufma.portifolium.repository;
import com.ufma.portifolium.model.dto.AlunoDTO;
import com.ufma.portifolium.model.entities.Aluno;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlunoRepository extends JpaRepository <Aluno, Long>{
    Boolean existsByMatricula(String matricula);
    Optional<Aluno> findByMatricula(String matricula);

    @Query("select new com.ufma.portifolium.model.dto.AlunoDTO(a.id, a.nome, u.descricao) "+
            "from Aluno a, Usuario u " +
            "where a.matricula=u.codigoAcesso")
    List<AlunoDTO> findAllAlunos();
}
