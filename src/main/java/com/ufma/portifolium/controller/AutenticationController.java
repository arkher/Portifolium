package com.ufma.portifolium.controller;

import com.ufma.portifolium.model.dto.UsuarioLoginDTO;
import com.ufma.portifolium.model.exceptions.UsuarioInvalidoException;
import com.ufma.portifolium.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/login")
public class AutenticationController {
    UsuarioService usuarioService;

    @Autowired
    public AutenticationController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody UsuarioLoginDTO usuarioDTO){
        try {
            boolean logado = usuarioService.efetuarLogin(usuarioDTO.getLogin(), usuarioDTO.getSenha());
            if(logado) return new ResponseEntity(true, HttpStatus.OK);
        } catch (UsuarioInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }
}
