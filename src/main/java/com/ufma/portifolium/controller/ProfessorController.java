package com.ufma.portifolium.controller;


import com.ufma.portifolium.model.entities.Professor;
import com.ufma.portifolium.model.exceptions.ProfessorInvalidoException;
import com.ufma.portifolium.service.ProfessorService;

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
@RequestMapping("/professores")
public class ProfessorController {
    ProfessorService professorService;
    
    @Autowired
    public ProfessorController(ProfessorService professorService){ this.professorService = professorService; }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Professor professor){
        try {
            Professor salvo = professorService.salvar(professor);
            return new ResponseEntity(salvo, HttpStatus.CREATED);
        } catch (ProfessorInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity recuperarProfessorPorCodigo(@RequestParam("professor") String codigo){
        try {
            Professor professor = professorService.recuperarPorCodigo(codigo);
            return new ResponseEntity(professor, HttpStatus.OK);
        } catch (ProfessorInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
