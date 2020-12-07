package com.ufma.portifolium.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufma.portifolium.model.dto.UsuarioDTO;
import com.ufma.portifolium.model.entities.Usuario;
import com.ufma.portifolium.service.UsuarioService;
import com.ufma.portifolium.utils.UsuarioFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest (controllers = UsuarioController.class)
@AutoConfigureMockMvc
public class UsuarioControllerTest {
    static final String API = "/usuarios";

    @Autowired
    MockMvc mvc;

    @MockBean
    UsuarioService usuarioService;

    /* 
        salvar
    */
    @Test
    public void deveRetornarStatusCriado_QuandoSalvarComSucesso() throws Exception {
        // given
        Usuario recebido = Usuario.builder().build();
        
        // when
        when(usuarioService.salvar(any(Usuario.class))).thenReturn(UsuarioDTO.builder().build());
        String json = new ObjectMapper().writeValueAsString(recebido);

        // then
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
}
