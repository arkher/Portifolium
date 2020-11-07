package com.ufma.portifolium.service;

import java.util.List;
import java.util.Optional;

import com.ufma.portifolium.model.entities.Usuario;
import com.ufma.portifolium.repository.UsuarioRepository;
import com.ufma.portifolium.service.exceptions.AutenticacaoException;
import com.ufma.portifolium.service.exceptions.CadastroException;
import com.ufma.portifolium.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    
    UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public boolean efetuarLogin(String codigoAcesso, String hashSenha){
        Optional<Usuario> usuario = usuarioRepository.findByCodigoAcesso(codigoAcesso);
        if(!usuario.isPresent()) throw new AutenticacaoException("Erro de Autenticação. Código de acesso não encontrado.");
        if(!usuario.get().getSenha().equals(hashSenha)) throw new AutenticacaoException("Erro de Autenticação. Senha incorreta.");
        return true;
    }

    @Transactional
    public Usuario salvar(Usuario usuario){
        verificarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> recuperarUsuarios(){ return usuarioRepository.findAll(); }

    public List<Usuario> buscar(Usuario filtro){
        Example<Usuario> example = Example.of(
            filtro, ExampleMatcher.matching()
                    .withIgnoreCase()
                    .withStringMatcher(StringMatcher.CONTAINING)
        );
        return usuarioRepository.findAll(example);
    }

    private void verificarUsuario(Usuario usuario){
        if(usuario == null) throw new CadastroException("Um usuário válido deve ser informado.");
        if(usuario.getCodigoAcesso() == null || usuario.getCodigoAcesso().equals("")) 
            throw new CadastroException("Código de acesso deve ser informado.");
        if(usuario.getSenha() == null || usuario.getSenha().equals("")) 
            throw new CadastroException("Senha deve ser informada.");
        if(usuario.getTipoUsuario() == null) throw new CadastroException("Tipo de usuário deve ser informado.");
        if(Utils.isNumeric(usuario.getCodigoAcesso())) throw new CadastroException("Um código válido deve ser informado (Campo somente numérico).");

        boolean usuarioJaCadastrado = usuarioRepository.existsByCodigoAcesso(usuario.getCodigoAcesso());
        if(usuarioJaCadastrado) throw new CadastroException("Um usuário com este código de acesso já foi cadastrado");
    }

    public void remover(Usuario usuario){
        verificarUsuario(usuario);
        usuarioRepository.delete(usuario);
    }

    public void remover(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()) remover(usuario.get());
    }

}