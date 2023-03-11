package com.example.drones.service;

import java.util.List;

import com.example.drones.entity.Drone;

public interface DroneServiceInterface {
	// Save operation
	Drone saveDrone(Drone drone);

	// Read operation
	List<Drone> fetchDroneList();

	// Update operation
	Drone updateDrone(Drone drone, Long droneId);

	// Delete operation
	void deleteDroneById(Long droneId);
}
