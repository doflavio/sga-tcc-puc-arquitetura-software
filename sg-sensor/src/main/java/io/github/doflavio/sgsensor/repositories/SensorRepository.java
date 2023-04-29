package io.github.doflavio.sgsensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.doflavio.sgsensor.models.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Integer>{
}
