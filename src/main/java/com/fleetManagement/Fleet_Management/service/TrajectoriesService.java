package com.fleetManagement.Fleet_Management.service;

import com.fleetManagement.Fleet_Management.model.TrajectoriesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrajectoriesService {
    Page<TrajectoriesModel> findAll(Pageable pageable);
    Page<TrajectoriesModel> findByTaxiId(Integer id, Pageable pageable);
}
