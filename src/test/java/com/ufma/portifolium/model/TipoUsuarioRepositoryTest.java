package com.ufma.portifolium.model;

import java.util.Optional;

import com.ufma.portifolium.entities.TipoUsuario;
import com.ufma.portifolium.repository.TipoUsuarioRepository;

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
public class TipoUsuarioRepositoryTest {
    @Autowired
    TipoUsuarioRepository repository;

    @Test
    public void deveSalvarTipoUsuario() {
        TipoUsuario tipoUsuario = TipoUsuario.builder()
                                .descricao("um tipo de usuario qualquer")
                                .build();

        TipoUsuario salvo = repository.save(tipoUsuario);

        Assertions.assertNotNull(salvo);
        Assertions.assertEquals(tipoUsuario, salvo);
        Assertions.assertNotNull(salvo.getId());
        repository.delete(salvo);
    }

    @Test
    public void deveRemoverTipoUsuario() {
        TipoUsuario tipoUsuario = TipoUsuario.builder()
                                .descricao("um tipo de usuario qualquer")
                                .build();

        TipoUsuario salvo = repository.save(tipoUsuario);
        Long id = salvo.getId();
        repository.deleteById(id);

        Optional<TipoUsuario> temp = repository.findById(id);
        Assertions.assertFalse(temp.isPresent());
    }

}
