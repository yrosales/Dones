package com.example.drones.repository;

import com.example.drones.entity.Medication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Annotation
@Repository

// Interface extending CrudRepository
public interface MedicationRepository extends CrudRepository<Medication, Long> {
}
