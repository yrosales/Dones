package com.example.drones.exception;

public class MedicationNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MedicationNotFoundException(Long id) {
		super("Medication " + id + " not found");
	}

}
