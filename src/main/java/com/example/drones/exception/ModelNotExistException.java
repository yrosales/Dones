package com.example.drones.exception;

public class ModelNotExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ModelNotExistException(String model) {
		super("Model " + model + " not exist");
	}

}
