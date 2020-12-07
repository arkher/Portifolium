package com.ufma.portifolium.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufma.portifolium.model.dto.AlunoDTO;
import com.ufma.portifolium.model.entities.Aluno;
import com.ufma.portifolium.service.AlunoService;
import com.ufma.portifolium.service.ProjetoService;
import com.ufma.portifolium.utils.AlunoFactory;

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
@WebMvcTest (controllers = AlunoController.class)
@AutoConfigureMockMvc
public class AlunoControllerTest {
    static final String API = "/alunos";

    @Autowired
    MockMvc mvc;

    @MockBean
    AlunoService alunoService;

    @MockBean
    ProjetoService projetoService;

    /* 
        salvar
    */
    @Test
    public void deveRetornarStatusCriado_QuandoSalvarComSucesso() throws Exception {
        // given
        Aluno recebido = AlunoFactory.buildALuno();
        Aluno salvo = AlunoFactory.buildALuno();
        
        // when
        when(alunoService.salvar(any(Aluno.class))).thenReturn(salvo);
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
    public void deveRetornarStatusOk_QuandoHouveremAlunosCadastrados() throws Exception {
        // given
        Aluno a1 = AlunoFactory.buildALuno();
        Aluno a2 = AlunoFactory.buildALuno();
        List<AlunoDTO> alunos = new ArrayList<>();
        alunos.add(new AlunoDTO(a1));
        alunos.add(new AlunoDTO(a2));

        // when
        when(alunoService.recuperarAlunos()).thenReturn(alunos);

        // then
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }
    
}
