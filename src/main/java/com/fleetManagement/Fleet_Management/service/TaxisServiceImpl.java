package com.fleetManagement.Fleet_Management.service;


import com.fleetManagement.Fleet_Management.model.TaxisModel;
import com.fleetManagement.Fleet_Management.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaxisServiceImpl implements TaxiService {

    @Autowired
    private TaxiRepository taxiRepository;

    @Override
    public Page<TaxisModel> findAll(Pageable pageable) {
        return taxiRepository.findAll(pageable);
    }

    @Override
    public Optional<TaxisModel> findById(Integer id) {
        return taxiRepository.findById(id);
    }
}
