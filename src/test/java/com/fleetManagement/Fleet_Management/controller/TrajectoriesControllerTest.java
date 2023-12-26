package com.fleetManagement.Fleet_Management.controller;

import com.fleetManagement.Fleet_Management.model.TaxisModel;
import com.fleetManagement.Fleet_Management.model.TrajectoriesModel;
import com.fleetManagement.Fleet_Management.repository.TrajectoriesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
        trajectories.setDate(ZonedDateTime.parse("2008-02-08T20:12:42-02:00[America/Sao_Paulo]").toInstant());
        trajectories.setLatitude(116.43121);
        trajectories.setLongitude(39.92576);
        trajectories.setTaxi(taxi);
        ArrayList<TrajectoriesModel> list = new ArrayList<>();
        list.add(trajectories);
        Page<TrajectoriesModel> page = new PageImpl<>(list);
        Mockito.when(trajectoriesRepository.findAll(any(Pageable.class))).thenReturn(page);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/trajectories"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().json("{\"content\":[{\"trajectories_id\":1234,\"date\":\"2008-02-08 20:12:42\",\"latitude\":116.43121,\"longitude\":39.92576,\"taxi\":{\"plate\":\"JHG-7856\",\"id\":5521}}]}"));

    }

    @DisplayName("Retorna trajetos pelo ID do taxi")
    @Test
    void retornaTrajetosPeloId() throws Exception {
        // Configuração do estado do objeto TaxisModel para simular um cenário em que um táxi com o ID fornecido é encontrado.
        Integer taxiId = 10133;

        TaxisModel taxi = new TaxisModel();
        taxi.setId(taxiId);
        taxi.setPlate("PAOF-6727");

        TrajectoriesModel trajectory = new TrajectoriesModel();
        trajectory.setTrajectories_id(3243);
        trajectory.setDate(ZonedDateTime.parse("2008-02-02T11:33:17-02:00[America/Sao_Paulo]").toInstant());
        trajectory.setLatitude(116.58808);
        trajectory.setLongitude(39.91051);
        trajectory.setTaxi(taxi);
        ArrayList<TrajectoriesModel> list = new ArrayList<>();
        list.add(trajectory);
        Page<TrajectoriesModel> page = new PageImpl<>(list);

        // Configura o comportamento simulado do TaxiRepository quando o método findByTaxiId é chamado
        Mockito.when(trajectoriesRepository.findByTaxiId(eq(taxiId), any(Pageable.class))).thenReturn(page);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/trajectories/{id}",taxiId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().json("{\"content\":[{\"trajectories_id\":3243,\"date\":\"2008-02-02 11:33:17\",\"latitude\":116.58808,\"longitude\":39.91051,\"taxi\":{\"plate\":\"PAOF-6727\",\"id\":10133}}]}"));
    }

    @DisplayName("Retornar a ultima localização pelo ID do taxi")
    @Test
    void retornaUltimaLocalizacao() throws Exception {
        Integer taxiId = 10133;

        TaxisModel taxi = new TaxisModel();
        taxi.setId(taxiId);
        taxi.setPlate("PAOF-6727");

        TrajectoriesModel lastLocation = new TrajectoriesModel();
        lastLocation.setTrajectories_id(3243);
        lastLocation.setDate(ZonedDateTime.parse("2008-02-02T11:33:17-02:00[America/Sao_Paulo]").toInstant());
        lastLocation.setLatitude(116.58808);
        lastLocation.setLongitude(39.91051);
        lastLocation.setTaxi(taxi);

        Mockito.when(trajectoriesRepository.findLastLocationByTaxiId(eq(taxiId), any(PageRequest.class)))
                .thenReturn(Collections.singletonList(lastLocation));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/trajectories/last-location/{id}", taxiId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().json("{\"trajectories_id\":3243,\"date\":\"2008-02-02 11:33:17\",\"latitude\":116.58808,\"longitude\":39.91051,\"taxi\":{\"plate\":\"PAOF-6727\",\"id\":10133}}"));

    }

}
