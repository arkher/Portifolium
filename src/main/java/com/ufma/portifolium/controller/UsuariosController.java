package com.ufma.portifolium.controller;

import com.ufma.portifolium.model.dto.UsuarioDTO;
import com.ufma.portifolium.model.dto.UsuarioLoginDTO;
import com.ufma.portifolium.model.entities.Usuario;
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
@RequestMapping("/usuarios")
public class UsuariosController {
    
    UsuarioService usuarioService;

    @Autowired
    public UsuariosController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Usuario usuario){
        try {
            Usuario salvo = usuarioService.salvar(usuario);
            return new ResponseEntity(salvo, HttpStatus.CREATED);
        } catch (UsuarioInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
