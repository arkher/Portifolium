package com.ufma.portifolium.model;

import java.util.Optional;

import com.ufma.portifolium.entities.Usuario;
import com.ufma.portifolium.repository.UsuarioRepository;
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
public class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository repository;

    @Test
    public void deveSalvarUsuario() {
        Usuario usuario = UsuarioFactory.buildUsuarioAluno();

        Usuario salvo = repository.save(usuario);

        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(usuario, salvo);
        Assertions.assertNotNull(salvo.getId());
    }

    @Test
    public void deveRemoverUsuario() {
        Usuario usuario = UsuarioFactory.buildUsuarioAluno();

        Usuario salvo = repository.save(usuario);
        Long id = salvo.getId();
        repository.deleteById(id);

        Optional<Usuario> temp = repository.findById(id);
        Assertions.assertFalse(temp.isPresent());
    }
}
