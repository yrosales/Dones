package com.example.drones.service;

import java.util.List;

import com.example.drones.entity.Medication;

public interface MedicationServiceInterface {
	// Save operation
	Medication saveMedication(Medication medication);

	// Read operation
	List<Medication> fetchMedicationList();

	// Update operation
	Medication updateMedication(Medication medication, Long medicationId);

	// Delete operation
	void deleteMedicationById(Long medicationId);
}
