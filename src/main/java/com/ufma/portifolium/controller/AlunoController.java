package com.ufma.portifolium.controller;

import java.util.List;

import com.ufma.portifolium.model.entities.Aluno;
import com.ufma.portifolium.model.exceptions.AlunoInvalidoException;
import com.ufma.portifolium.service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    AlunoService alunoService;
    
    @Autowired
    public AlunoController(AlunoService alunoService){ this.alunoService = alunoService; }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Aluno aluno){
        try {
            Aluno salvo = alunoService.salvar(aluno);
            return new ResponseEntity(salvo, HttpStatus.CREATED);
        } catch (AlunoInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity recuperarAlunoPorMatricula(@RequestParam("aluno") String matricula){
        try {
            Aluno aluno = alunoService.recuperarPorMatricula(matricula);
            return new ResponseEntity(aluno, HttpStatus.OK);
        } catch (AlunoInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
