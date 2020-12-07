package com.ufma.portifolium.controller;


import java.util.List;

import com.ufma.portifolium.model.entities.Tecnologia;
import com.ufma.portifolium.model.exceptions.TecnologiaInvalidaException;
import com.ufma.portifolium.service.TecnologiaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tecnologias")
public class TecnologiaController {
    TecnologiaService tecnologiaService;
    
    @Autowired
    public TecnologiaController(TecnologiaService tecnologiaService){ this.tecnologiaService = tecnologiaService; }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Tecnologia tecnologia){
        try {
            Tecnologia salvo = tecnologiaService.salvar(tecnologia);
            return new ResponseEntity(salvo, HttpStatus.CREATED);
        } catch (TecnologiaInvalidaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity recuperarTecnologias(){
        try {
            List<Tecnologia> tecnologias = tecnologiaService.recuperarTecnologias();
            return new ResponseEntity(tecnologias, HttpStatus.OK);
        } catch (TecnologiaInvalidaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
