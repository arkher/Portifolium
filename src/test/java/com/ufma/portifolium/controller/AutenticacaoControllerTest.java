package com.ufma.portifolium.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufma.portifolium.model.dto.UsuarioDTO;
import com.ufma.portifolium.model.dto.UsuarioLoginDTO;
import com.ufma.portifolium.service.UsuarioService;

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
@WebMvcTest (controllers = AutenticacaoController.class)
@AutoConfigureMockMvc
public class AutenticacaoControllerTest {
    static final String API = "/login";

    @Autowired
    MockMvc mvc;

    @MockBean
    UsuarioService usuarioService;

    /* 
        salvar
    */
    @Test
    public void deveRetornarStatusOk_QuandoLogarComSucesso() throws Exception {
        // given
        UsuarioLoginDTO usuarioLogin = UsuarioLoginDTO.builder()
                                        .login("123123").senha("asdasd").build();
        
        UsuarioDTO usuarioLogado = UsuarioDTO.builder().build();               
        // when
        when(usuarioService.efetuarLogin(anyString(), anyString())).thenReturn(usuarioLogado);
        String json = new ObjectMapper().writeValueAsString(usuarioLogin);

        // then
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
