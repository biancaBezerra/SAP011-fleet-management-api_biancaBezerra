package com.fleetManagement.Fleet_Management.service;

import com.fleetManagement.Fleet_Management.model.TrajectoriesModel;
import com.fleetManagement.Fleet_Management.repository.TrajectoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public TrajectoriesModel getLastLocationByTaxiId(Integer id) {
        List<TrajectoriesModel> trajectories = trajectoriesRepository.findLastLocationByTaxiId(id, PageRequest.of(0, 1));
        return trajectories.isEmpty() ? null : trajectories.get(0);
    }
}
