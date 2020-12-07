package com.ufma.portifolium.controller;

import com.ufma.portifolium.model.dto.UsuarioDTO;
import com.ufma.portifolium.model.dto.UsuarioLoginDTO;
import com.ufma.portifolium.model.exceptions.UsuarioInvalidoException;
import com.ufma.portifolium.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    UsuarioService usuarioService;

    @Autowired
    public AutenticacaoController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody UsuarioLoginDTO usuarioLoginDTO){
        try {
            UsuarioDTO logado = usuarioService.efetuarLogin(usuarioLoginDTO.getLogin(), usuarioLoginDTO.getSenha());
            if(logado!=null) return new ResponseEntity(logado, HttpStatus.OK);
        } catch (UsuarioInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }
}
