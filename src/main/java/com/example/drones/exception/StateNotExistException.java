package com.example.drones.exception;

public class StateNotExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StateNotExistException(String state) {
		super("State " + state + " not found");
	}

}
