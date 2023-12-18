package com.fleetManagement.Fleet_Management.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "taxis")
public class TaxisModel implements Serializable { // serializable é tudo que transformamos em texto
    @Id
    @Column(name = "id")
    private Integer Id; //encapsulamento, só pode ser acessada dentro da classe TaxisModel

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
