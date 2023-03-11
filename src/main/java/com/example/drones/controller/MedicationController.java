package com.example.drones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.drones.entity.Medication;
import com.example.drones.service.MedicationService;

@RestController
public class MedicationController {

	@Autowired
	private MedicationService medicationService;

	@PostMapping("/medications")
	public Medication saveMedication(@RequestBody Medication medication) {
		return medicationService.saveMedication(medication);
	}

	@GetMapping("/medications")
	public List<Medication> fetchMedicationList() {
		return medicationService.fetchMedicationList();
	}

	@PutMapping("/medications/{id}")
	public Medication updateMedication(@RequestBody Medication medication, @PathVariable("id") Long medicationId) {
		return medicationService.updateMedication(medication, medicationId);
	}

	@DeleteMapping("/medications/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long medicationId) {
		medicationService.deleteMedicationById(medicationId);
		return "Deleted Successfully";
	}
}
