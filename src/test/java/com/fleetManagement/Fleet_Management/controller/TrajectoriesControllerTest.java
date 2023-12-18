package com.fleetManagement.Fleet_Management.controller;

import com.fleetManagement.Fleet_Management.model.TaxisModel;
import com.fleetManagement.Fleet_Management.model.TrajectoriesModel;
import com.fleetManagement.Fleet_Management.repository.TrajectoriesRepository;
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

import java.util.ArrayList;

import static java.sql.Timestamp.valueOf;

@SpringBootTest
@AutoConfigureMockMvc
public class TrajectoriesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrajectoriesRepository trajectoriesRepository;

    @DisplayName("Retorna a localização de todos os taxis")
    @Test
    void retornaTodosTrajetos() throws Exception {
        TaxisModel taxi = new TaxisModel();
        taxi.setId(5521);
        taxi.setPlate("JHG-7856");

        TrajectoriesModel trajectories = new TrajectoriesModel();
        trajectories.setTrajectories_id(1234);
        trajectories.setDate(valueOf("2008-02-08 20:12:42.000"));
        trajectories.setLatitude(116.43121);
        trajectories.setLongitude(39.92576);
        trajectories.setTaxi(taxi);
        ArrayList<TrajectoriesModel> list = new ArrayList<>();
        list.add(trajectories);
        Page<TrajectoriesModel> page = new PageImpl<>(list);
        Mockito.when(trajectoriesRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(page);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/trajectories"))
                .andDo(MockMvcResultHandlers.print());
    }

//    @DisplayName("Retona trajetos pelo ID do taxi")
//    @Test
//    void retornaTrajetosPeloId()
}
