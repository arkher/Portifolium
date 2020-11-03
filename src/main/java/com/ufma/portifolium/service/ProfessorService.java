package com.ufma.portifolium.service;

import java.util.List;
import java.util.Optional;

import com.ufma.portifolium.entities.Professor;
import com.ufma.portifolium.repository.ProfessorRepository;
import com.ufma.portifolium.service.exceptions.CadastroException;
import com.ufma.portifolium.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfessorService {
    
    ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }
    
    public List<Professor> recuperarProfessores(){ return professorRepository.findAll(); }
    
    public List<Professor> buscar(Professor filtro){
        Example<Professor> example = Example.of(
            filtro, ExampleMatcher.matching()
                    .withIgnoreCase()
                    .withStringMatcher(StringMatcher.CONTAINING)
        );
        return professorRepository.findAll(example);
    }

    public Professor recuperarPorCodigo(String codigo){
        Optional<Professor> professor = professorRepository.findByCodigo(codigo);
        if(professor.isPresent()) return professor.get();
        return null;
    }

    @Transactional
    public Professor salvar(Professor professor){
        verificarProfessor(professor);
        return professorRepository.save(professor);
    }

    private void verificarProfessor(Professor professor){
        if(professor == null) throw new CadastroException("Um Professor válido deve ser informado.");
        if(professor.getNome() == null || professor.getNome().equals("")) throw new CadastroException("Um nome válido deve ser informado.");
        if(professor.getCodigo() == null || professor.getCodigo().equals("")) throw new CadastroException("Um código válido deve ser informado.");
        if(Utils.isNumeric(professor.getCodigo())) throw new CadastroException("Um código válido deve ser informado (Campo somente numérico).");

        boolean professorJaCadastrado = professorRepository.existsByCodigo(professor.getCodigo());
        if(professorJaCadastrado) throw new CadastroException("Professor já cadastrado.");
    }

    public void remover(Professor professor){
        verificarProfessor(professor);
        professorRepository.delete(professor);
    }

    public void removerPorId(Long id){
        Optional<Professor> professor = professorRepository.findById(id);
        if(professor.isPresent()) remover(professor.get());
    }

}
