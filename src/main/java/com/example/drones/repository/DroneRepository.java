package com.example.drones.repository;

import com.example.drones.entity.Drone;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Annotation
@Repository

// Interface extending CrudRepository
public interface DroneRepository extends CrudRepository<Drone, Long> {
}
