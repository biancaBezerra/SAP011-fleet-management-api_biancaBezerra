package com.fleetManagement.Fleet_Management.service;

import com.fleetManagement.Fleet_Management.model.TaxisModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaxiService {

    Page<TaxisModel> findAll(Pageable pageable);

    Optional<TaxisModel> findById(Integer id);
}

