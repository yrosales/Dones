package com.example.drones.exception;

public class OverLoadException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OverLoadException(Long id){
		super("Drone " + id + " over load");
	}
}
