package com.fleetManagement.Fleet_Management.repository;

import com.fleetManagement.Fleet_Management.model.TaxisModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxiRepository extends PagingAndSortingRepository<TaxisModel, Integer> {
    Optional<TaxisModel> findById(Integer id);
}
