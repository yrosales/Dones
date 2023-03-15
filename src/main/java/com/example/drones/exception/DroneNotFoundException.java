package com.example.drones.exception;

public class DroneNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DroneNotFoundException(Long id) {
		super("Drone " + id + " not found");
	}

}
