package com.example.drones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.drones.entity.Medication;
import com.example.drones.repository.MedicationRepository;

@Service

public class MedicationService implements MedicationServiceInterface {
	
	@Autowired
    private MedicationRepository medicationRepository;

	public Medication saveMedication(Medication medication) {
		return medicationRepository.save(medication);
	}

	@Override
	public List<Medication> fetchMedicationList() {
		return (List<Medication>) medicationRepository.findAll();
	}

	@Override
	public Medication updateMedication(Medication newMedication, Long newMedicationId) {
		Medication medication = medicationRepository.findById(newMedicationId).get();
		medication.setName(newMedication.getName());
		medication.setWeight(newMedication.getWeight());
		medication.setCode(newMedication.getCode());
		return medicationRepository.save(medication);
	}

	@Override
	public void deleteMedicationById(Long medicationId) {
		medicationRepository.deleteById(medicationId);
	}

}
