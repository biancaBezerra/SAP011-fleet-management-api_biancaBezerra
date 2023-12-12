package com.fleetManagement.Fleet_Management.repository;

import com.fleetManagement.Fleet_Management.model.TrajectoriesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrajectoriesRepository extends PagingAndSortingRepository<TrajectoriesModel,Integer> {

    @Query("FROM TrajectoriesModel g where g.taxi.id = :taxiId")
    Page<TrajectoriesModel> findByTaxiId(@Param("taxiId") Integer taxiId, Pageable pageable);

    @Query("FROM TrajectoriesModel g WHERE g.taxi.id = :taxiId ORDER BY g.date DESC")
    List<TrajectoriesModel> findLastLocationByTaxiId(@Param("taxiId") Integer taxiId, Pageable pageable);

}


