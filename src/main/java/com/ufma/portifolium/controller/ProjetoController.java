package com.ufma.portifolium.controller;


import java.util.List;

import com.ufma.portifolium.model.dto.ProjetoValidacaoDTO;
import com.ufma.portifolium.model.entities.Projeto;
import com.ufma.portifolium.model.exceptions.ProjetoInvalidoException;
import com.ufma.portifolium.service.ProjetoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    ProjetoService projetoService;
    
    @Autowired
    public ProjetoController(ProjetoService projetoService){ this.projetoService = projetoService; }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Projeto projeto){
        try {
            Projeto salvo = projetoService.salvar(projeto);
            return new ResponseEntity(salvo, HttpStatus.CREATED);
        } catch (ProjetoInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity recuperarProjetos(){
        try {
            List<Projeto> projetos = projetoService.recuperarProjetos();
            return new ResponseEntity(projetos, HttpStatus.OK);
        } catch (ProjetoInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity validarProjeto(@PathVariable("id") Long idProjeto, @RequestBody ProjetoValidacaoDTO validacao){
        try {
            Projeto validado = projetoService.validar(validacao, idProjeto);
            return new ResponseEntity(validado, HttpStatus.OK);
        } catch (ProjetoInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
