package com.fleetManagement.Fleet_Management.service;

import com.fleetManagement.Fleet_Management.model.TaxisModel;
import com.fleetManagement.Fleet_Management.model.TrajectoriesModel;
import com.fleetManagement.Fleet_Management.repository.TrajectoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrajectoriesServiceImpl implements TrajectoriesService{

    @Autowired
    private TrajectoriesRepository trajectoriesRepository;

    @Override
    public Page<TrajectoriesModel> findAll(Pageable pageable) {
        return trajectoriesRepository.findAll(pageable);
    }

    @Override
    public Page<TrajectoriesModel> findByTaxiId(Integer id, Pageable pageable) {
        return trajectoriesRepository.findByTaxiId(id,pageable);
    }
}
