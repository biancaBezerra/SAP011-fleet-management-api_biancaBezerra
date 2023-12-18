package com.fleetManagement.Fleet_Management.controller;


import com.fleetManagement.Fleet_Management.model.TaxisModel;
import com.fleetManagement.Fleet_Management.repository.TaxiRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
public class TaxiControllerTest {
    @Autowired // É o mesmo retorno de um new....
    private MockMvc mockMvc; //é o insomnia dos testes

    @MockBean
    private TaxiRepository taxiRepository;

    @DisplayName("Retornar todos os taxis")
    @Test
    void retornarTodosTaxis() throws Exception {
        // Cria um objeto TaxisModel simulado
        TaxisModel taxi = new TaxisModel();
        taxi.setId(5521);
        taxi.setPlate("JHG-7856");
        // Cria uma lista simulada de TaxisModel
        ArrayList<TaxisModel> list = new ArrayList<>();
        list.add(taxi);
        // Cria uma página simulada contendo a lista de táxis simulada
        Page<TaxisModel> page = new PageImpl<>(list);
        // Configura o comportamento simulado do TaxiRepository quando o método findAll é chamado
        Mockito.when(taxiRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(page);
        // Realiza uma solicitação HTTP GET para o endpoint
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/taxis"))
                .andDo(MockMvcResultHandlers.print());
                //.andExpect();

    }

    @DisplayName("Retornar um taxi pelo id digitado")
    @Test
    void retornarUmTaxiPeloId() throws Exception {
        //configurando o estado do objeto taxisModel para simular um cenário em que um táxi com o ID fornecido é encontrado.
        Integer taxiId = 7249;
        TaxisModel taxisModel = new TaxisModel();
        taxisModel.setId(taxiId);
        taxisModel.setPlate("JHG-7856");
        // Configura o comportamento simulado do TaxiRepository quando o método findAll é chamado
        Mockito.when(taxiRepository.findById(taxiId)).thenReturn(Optional.of(taxisModel));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/taxis/{id}", taxiId))
                .andDo(MockMvcResultHandlers.print());
    }
}