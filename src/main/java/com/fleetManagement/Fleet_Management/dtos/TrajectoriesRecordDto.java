package com.fleetManagement.Fleet_Management.dtos;

import java.sql.Timestamp;

public record TrajectoriesRecordDto(Double latitude, Double longitude, Timestamp date, Integer taxi) {
}
