package com.ufma.portifolium.controller;

import com.ufma.portifolium.model.entities.Projeto;
import com.ufma.portifolium.service.ProjetoService;
import com.ufma.portifolium.utils.ProjetoFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest (controllers = ProjetoController.class)
@AutoConfigureMockMvc
public class ProjetoControllerTest {
    static final String API = "/projetos";

    @Autowired
    MockMvc mvc;
    
    @MockBean
    ProjetoService projetoService;

    @Test
    public void deveRetornarStatusCriado_QuandoSalvarComSucesso() throws Exception {
        // given
        Projeto recebido = Projeto.builder().build();
        
        // when
        when(projetoService.salvar(any(Projeto.class))).thenReturn(Projeto.builder().build());
        String json = new ObjectMapper().writeValueAsString(recebido);

        // then
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    /*
        recuperar
    */
    @Test
    public void deveRetornarStatusOk_QuandoHouveremProjetosCadastrados() throws Exception {
        // given
        Projeto p1 = ProjetoFactory.buildProjeto();
        Projeto p2 = ProjetoFactory.buildProjeto();
        List<Projeto> projetos = new ArrayList<>();
        projetos.add(p1);
        projetos.add(p2);

        // when
        when(projetoService.recuperarProjetos()).thenReturn(projetos);

        // then
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }
    
}
