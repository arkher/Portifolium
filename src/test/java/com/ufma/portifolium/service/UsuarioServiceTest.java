package com.ufma.portifolium.service;

import com.ufma.portifolium.model.entities.Usuario;
import com.ufma.portifolium.repository.TipoUsuarioRepository;
import com.ufma.portifolium.repository.UsuarioRepository;
import com.ufma.portifolium.model.exceptions.AutenticacaoException;
import com.ufma.portifolium.model.exceptions.UsuarioInvalidoException;
import com.ufma.portifolium.utils.UsuarioFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

    UsuarioService usuarioService;

    UsuarioRepository usuarioRepository;

    TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public UsuarioServiceTest(UsuarioService usuarioService, UsuarioRepository usuarioRepository,
                            TipoUsuarioRepository tipoUsuarioRepository ) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Test
    public void deveSalvarUsuarioAluno() {
        Usuario usuario = UsuarioFactory.buildUsuarioAluno();

        tipoUsuarioRepository.save(usuario.getTipoUsuario());
        
        Usuario salvo = usuarioService.salvar(usuario);

        Assertions.assertNotNull(salvo);
        Assertions.assertNotNull(salvo.getId());

        usuarioRepository.delete(salvo);
    }

    @Test
    public void deveGerarErroAoTentarSalvarSemCodigo() {
        Usuario usuario = UsuarioFactory.buildUsuarioAluno();
        usuario.setCodigoAcesso(null);

        Assertions.assertThrows(UsuarioInvalidoException.class, () -> usuarioService.salvar(usuario),
                "Código de acesso deve ser informado.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarSemSenha() {
        Usuario usuario = UsuarioFactory.buildUsuarioAluno();
        usuario.setSenha(null);

        Assertions.assertThrows(UsuarioInvalidoException.class, () -> usuarioService.salvar(usuario),
                "Senha deve ser informada.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarSemTipoUsuario() {
        Usuario usuario = UsuarioFactory.buildUsuarioAluno();
        usuario.setTipoUsuario(null);

        Assertions.assertThrows(UsuarioInvalidoException.class, () -> usuarioService.salvar(usuario),
                "Tipo de usuário deve ser informado.");
    }

    @Test
    public void deveGerarErroAoTentarSalvarCodigoInvalido() {
        Usuario usuario = UsuarioFactory.buildUsuarioAluno();
        usuario.setCodigoAcesso("pinguinssãoincríveis");

        Assertions.assertThrows(UsuarioInvalidoException.class, () -> usuarioService.salvar(usuario),
                "Um código válido deve ser informado (Campo somente numérico).");
    }

    @Test
    public void deveGerarErroAoTentarSalvarUsuarioJaCadastrado() {
        Usuario usuario = UsuarioFactory.buildUsuarioAluno();

        Usuario salvo = usuarioRepository.save(usuario);

        Assertions.assertThrows(UsuarioInvalidoException.class, () -> usuarioService.salvar(salvo),
                "Um usuário com este código de acesso já foi cadastrado");

        usuarioRepository.delete(salvo);
    }

    @Test
    public void deveAutenticarUsuario() {
        Usuario usuario = UsuarioFactory.buildUsuarioAluno();

        Usuario salvo = usuarioRepository.save(usuario);
        boolean resposta = usuarioService.efetuarLogin(salvo.getCodigoAcesso(), salvo.getSenha());

        Assertions.assertTrue(resposta);

        usuarioRepository.delete(salvo);
    }

    @Test
    public void deveGerarErroAutenticacaoCodigoNaoCadastrado() {

        Assertions.assertThrows(AutenticacaoException.class, () -> usuarioService.efetuarLogin("0000000", "123456"),
                "Erro de Autenticação. Código de acesso não encontrado.");
    }

    @Test
    public void deveGerarErroAutenticacaoSenhaIncorreta() {
        Usuario usuario = UsuarioFactory.buildUsuarioAluno();

        Usuario salvo = usuarioRepository.save(usuario);
        String codigo = salvo.getCodigoAcesso();

        Assertions.assertThrows(AutenticacaoException.class, () -> usuarioService.efetuarLogin(codigo, "0000000"),
                "Erro de Autenticação. Senha incorreta.");

        usuarioRepository.delete(salvo);
    }
}
