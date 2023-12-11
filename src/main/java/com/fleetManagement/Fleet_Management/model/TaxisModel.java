package com.fleetManagement.Fleet_Management.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "taxis")
public class TaxisModel implements Serializable {
    @Id
    @Column(name = "id")
    private Integer Id;

    @Column(name = "plate")
    private String plate;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
