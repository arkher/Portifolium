package com.ufma.portifolium.service;

import java.util.List;
import java.util.Optional;

import com.ufma.portifolium.model.dto.UsuarioDTO;
import com.ufma.portifolium.model.entities.Aluno;
import com.ufma.portifolium.model.entities.Professor;
import com.ufma.portifolium.model.entities.TipoUsuario;
import com.ufma.portifolium.model.entities.Usuario;
import com.ufma.portifolium.model.exceptions.AutenticacaoException;
import com.ufma.portifolium.model.exceptions.UsuarioInvalidoException;
import com.ufma.portifolium.repository.AlunoRepository;
import com.ufma.portifolium.repository.ProfessorRepository;
import com.ufma.portifolium.repository.TipoUsuarioRepository;
import com.ufma.portifolium.repository.UsuarioRepository;
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
    TipoUsuarioRepository tipoUsuarioRepository;
    AlunoRepository alunoRepository;
    ProfessorRepository professorRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, TipoUsuarioRepository tipoUsuarioRepository,
                            AlunoRepository alunoRepository, ProfessorRepository professorRepository){
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public UsuarioDTO efetuarLogin(String codigoAcesso, String hashSenha){
        Optional<Usuario> usuario = usuarioRepository.findByCodigoAcesso(codigoAcesso);
        if(!usuario.isPresent()) throw new AutenticacaoException("Erro de Autenticação. Código de acesso não encontrado.");
        if(!usuario.get().getSenha().equals(hashSenha)) throw new AutenticacaoException("Erro de Autenticação. Senha incorreta.");
        return getUsuario(usuario.get());
    }

    @Transactional
    public UsuarioDTO salvar(Usuario usuario){
        verificarUsuario(usuario);
        
        Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepository.findByDescricao(usuario.getTipoUsuario().getDescricao());
        if(tipoUsuario.isPresent()){
            usuario.setTipoUsuario(tipoUsuario.get());
            Usuario salvo = usuarioRepository.save(usuario);
            return getUsuario(salvo);
        } 
        else{
            throw new UsuarioInvalidoException("Tipo de usuário inválido.");
        }
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

    private UsuarioDTO getUsuario(Usuario usuario){
        if(usuario.getTipoUsuario().getDescricao().equals("aluno")){
            Optional<Aluno> aluno = alunoRepository.findByMatricula(usuario.getCodigoAcesso());
            if(aluno.isPresent())
                return new UsuarioDTO(usuario, aluno.get().getId(), aluno.get().getNome());
        } else if(usuario.getTipoUsuario().getDescricao().equals("professor")){
            Optional<Professor> professor = professorRepository.findByCodigo(usuario.getCodigoAcesso());
            if(professor.isPresent())
                return new UsuarioDTO(usuario, professor.get().getId(), professor.get().getNome());
        }
        return null;
    }

    private void verificarUsuario(Usuario usuario){
        if(usuario == null) throw new UsuarioInvalidoException("Um usuário válido deve ser informado.");
        if(usuario.getCodigoAcesso() == null || usuario.getCodigoAcesso().equals("")) 
            throw new UsuarioInvalidoException("Código de acesso deve ser informado.");
        if(usuario.getSenha() == null || usuario.getSenha().equals("")) 
            throw new UsuarioInvalidoException("Senha deve ser informada.");
        if(usuario.getTipoUsuario() == null) 
            throw new UsuarioInvalidoException("Tipo de usuário deve ser informado.");
        if(!Utils.isNumeric(usuario.getCodigoAcesso())) 
            throw new UsuarioInvalidoException("Um código válido deve ser informado (Campo somente numérico).");

        boolean usuarioJaCadastrado = usuarioRepository.existsByCodigoAcesso(usuario.getCodigoAcesso());
        if(usuarioJaCadastrado) throw new UsuarioInvalidoException("Um usuário com este código de acesso já foi cadastrado");
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