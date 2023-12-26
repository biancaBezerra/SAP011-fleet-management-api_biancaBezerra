package com.fleetManagement.Fleet_Management.controller;

import com.fleetManagement.Fleet_Management.model.TaxisModel;
import com.fleetManagement.Fleet_Management.model.TrajectoriesModel;
import com.fleetManagement.Fleet_Management.service.TrajectoriesService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/v1/trajectories")
public class TrajectoriesController {
    @Autowired
    TrajectoriesService trajectoriesService;

    @GetMapping
    public ResponseEntity<Page<TrajectoriesModel>> findAll(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(trajectoriesService.findAll(pageable));
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = TrajectoriesModel.class))})})

    @GetMapping("/{id}")
    public ResponseEntity<Page<TrajectoriesModel>> findByTaxiId(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<TrajectoriesModel> trajectories = trajectoriesService.findByTaxiId(id, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(trajectories);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema =
                    @Schema(implementation = TrajectoriesModel.class))})})

    @GetMapping("/last-location/{id}")
    public ResponseEntity<TrajectoriesModel> getLastLocation(@PathVariable Integer id) {
        TrajectoriesModel lastLocation = trajectoriesService.getLastLocationByTaxiId(id);
        return ResponseEntity.status(HttpStatus.OK).body(lastLocation);
    }

}
