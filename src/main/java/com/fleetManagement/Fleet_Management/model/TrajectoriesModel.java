package com.fleetManagement.Fleet_Management.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "trajectories")
public class TrajectoriesModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    @Column(name = "trajectories_id")
    private Integer trajectories_id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Brazil/East")
    @Column(name = "date")
    private Instant date;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "taxi_id")
    private TaxisModel taxi;

    public Integer getTrajectories_id() {
        return trajectories_id;
    }

    public void setTrajectories_id(Integer trajectories_id) {
        this.trajectories_id = trajectories_id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public TaxisModel getTaxi() {
        return taxi;
    }

    public void setTaxi(TaxisModel taxi) {
        this.taxi = taxi;
    }
}
