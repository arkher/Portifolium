package com.ufma.portifolium.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufma.portifolium.model.entities.Tecnologia;
import com.ufma.portifolium.service.TecnologiaService;
import com.ufma.portifolium.service.ProjetoService;
import com.ufma.portifolium.service.TecnologiaService;
import com.ufma.portifolium.utils.TecnologiaFactory;

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
@WebMvcTest (controllers = TecnologiaController.class)
@AutoConfigureMockMvc
public class TecnologiaControllerTest {
    static final String API = "/tecnologias";

    @Autowired
    MockMvc mvc;

    @MockBean
    TecnologiaService tecnologiaService;

    /* 
        salvar
    */
    @Test
    public void deveRetornarStatusCriado_QuandoSalvarComSucesso() throws Exception {
        // given
        Tecnologia recebido = TecnologiaFactory.buildTecnologia();
        Tecnologia salvo = TecnologiaFactory.buildTecnologia();
        
        // when
        when(tecnologiaService.salvar(any(Tecnologia.class))).thenReturn(salvo);
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
    public void deveRetornarStatusOk_QuandoHouveremTecnologiasCadastrados() throws Exception {
        // given
        Tecnologia t1 = TecnologiaFactory.buildTecnologia();
        Tecnologia t2 = TecnologiaFactory.buildTecnologia();
        List<Tecnologia> tecnologias = new ArrayList<>();
        tecnologias.add(t1);
        tecnologias.add(t2);

        // when
        when(tecnologiaService.recuperarTecnologias()).thenReturn(tecnologias);

        // then
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }
    
}
