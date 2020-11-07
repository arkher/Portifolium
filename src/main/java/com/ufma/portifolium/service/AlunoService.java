package com.ufma.portifolium.service;

import java.util.List;
import java.util.Optional;

import com.ufma.portifolium.model.entities.Aluno;
import com.ufma.portifolium.model.exceptions.AlunoInvalidoException;
import com.ufma.portifolium.repository.AlunoRepository;
import com.ufma.portifolium.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlunoService {
    
    AlunoRepository alunoRepository;
    
    @Autowired
    public AlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> recuperarAlunos(){ return alunoRepository.findAll(); }

    public List<Aluno> buscar(Aluno filtro){
        Example<Aluno> example = Example.of(
            filtro, ExampleMatcher.matching()
                    .withIgnoreCase()
                    .withStringMatcher(StringMatcher.CONTAINING)
        );
        return alunoRepository.findAll(example);
    }

    public Aluno recuperarPorMatricula(String matricula){
        Optional<Aluno> aluno = alunoRepository.findByMatricula(matricula);
        if(aluno.isPresent()) return aluno.get();
        return null;
    }

    @Transactional
    public Aluno salvar(Aluno aluno){
        verificarAluno(aluno);
        return alunoRepository.save(aluno);
    }

    private void verificarAluno(Aluno aluno){
        if(aluno == null) throw new AlunoInvalidoException("Um aluno válido deve ser informado.");
        if(aluno.getNome() == null || aluno.getNome().equals("")) throw new AlunoInvalidoException("Um nome válido deve ser informado.");
        if(aluno.getMatricula() == null || aluno.getMatricula().equals("")) throw new AlunoInvalidoException("Uma matrícula válida deve ser informada.");
        if(Utils.isNumeric(aluno.getMatricula())) throw new AlunoInvalidoException("Uma matrícula válida deve ser informada (Campo somente numérico).");

        boolean alunoJaCadastrado = alunoRepository.existsByMatricula(aluno.getMatricula());
        if(alunoJaCadastrado) throw new AlunoInvalidoException("Aluno já cadastrado.");
    }

    public void remover(Aluno aluno){
        verificarAluno(aluno);
        alunoRepository.delete(aluno);
    }

    public void remover(Long id){
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if(aluno.isPresent()) remover(aluno.get());
    }

}
