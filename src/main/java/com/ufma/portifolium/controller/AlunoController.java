package com.ufma.portifolium.controller;


import java.util.List;

import com.ufma.portifolium.model.dto.AlunoDTO;
import com.ufma.portifolium.model.entities.Aluno;
import com.ufma.portifolium.model.entities.Projeto;
import com.ufma.portifolium.model.exceptions.AlunoInvalidoException;
import com.ufma.portifolium.service.AlunoService;
import com.ufma.portifolium.service.ProjetoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    AlunoService alunoService;
    ProjetoService projetoService;
    
    @Autowired
    public AlunoController(AlunoService alunoService, ProjetoService projetoService){ 
        this.alunoService = alunoService; 
        this.projetoService = projetoService;
    }

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
    public ResponseEntity recuperarAlunos(){
        try {
            List<AlunoDTO> alunos = alunoService.recuperarAlunos();
            return new ResponseEntity(alunos, HttpStatus.OK);
        } catch (AlunoInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/projetos")
    public ResponseEntity recuperarProjetos(@PathVariable("id") Long idAluno){
        try {
            List<Projeto> projetos = projetoService.recuperarPorAluno(idAluno);
            return new ResponseEntity(projetos, HttpStatus.OK);
        } catch (AlunoInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
