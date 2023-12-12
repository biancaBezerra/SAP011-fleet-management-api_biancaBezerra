package com.fleetManagement.Fleet_Management.controller;


import com.fleetManagement.Fleet_Management.model.TaxisModel;
import com.fleetManagement.Fleet_Management.repository.TaxiRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
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

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class TaxiControllerTest {
    @Autowired
    private MockMvc mockMvc; //é o insomina dos testes

    @MockBean
    private TaxiRepository taxiRepository;

    @DisplayName("Retornar todos os taxis")
    @Test
    void retornarTodosTaxis() throws Exception {
        TaxisModel taxi = new TaxisModel();
        taxi.setId(5521);
        taxi.setPlate("JHG-7856");
        ArrayList<TaxisModel> list = new ArrayList<>();
        list.add(taxi);
        Page<TaxisModel> page = new PageImpl<>(list);
        Mockito.when(taxiRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(page);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/taxis")).andDo(MockMvcResultHandlers.print());
    }
}