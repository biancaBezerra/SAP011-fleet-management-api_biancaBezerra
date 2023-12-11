package com.fleetManagement.Fleet_Management.controller;

import com.fleetManagement.Fleet_Management.model.TaxisModel;
import com.fleetManagement.Fleet_Management.service.TaxiService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/taxis")
public class TaxiController {
    @Autowired
    TaxiService taxiService; //dá acesso aos métodos JPA quando houver necessidade

    //Listar todos os produtos que temos salvo na base método GET de leitura


    @GetMapping//acionamento URI /taxis
    public ResponseEntity<Page<TaxisModel>> findAll(Pageable pageable) { //no corpo ele vai ter uma Lista de taxis
        return ResponseEntity.status(HttpStatus.OK).body(taxiService.findAll(pageable)); //utilizamos o Repository e fildAll para listar todos os recursos salvos na base de dados
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = TaxisModel.class))})})

    @GetMapping("/{id}") //acionamento URI /taxis com um unico recurso especifico ID
    public ResponseEntity<Optional<TaxisModel>>findById(@PathVariable Integer id){ //vamos ter 2 tipos de retorno dependendo algumas situações
        return ResponseEntity.status(HttpStatus.OK).body(taxiService.findById(id));
    }

}
