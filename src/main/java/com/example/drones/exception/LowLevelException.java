package com.example.drones.exception;

public class LowLevelException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LowLevelException(Long id) {
		super("Drone " + id + " Low level");
	}

}
