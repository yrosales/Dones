package com.example.drones.service;

import java.util.List;

import com.example.drones.entity.Drone;
import com.example.drones.entity.Medication;
import com.example.drones.model.DroneState;

public interface DroneServiceInterface {
	// Save operation
	Drone saveDrone(Drone drone);

	// Read operation
	List<Drone> fetchDroneList();

	// Update operation
	Drone updateDrone(Drone drone, Long droneId);

	// Delete operation
	void deleteDroneById(Long droneId);
	
	//loading a drone with medication items
	Drone loadMedication(Long droneId, Long medicationId);
	
	//checking loaded medication items for a given drone
	List<Medication> getLoadedMedications (Long droneId);
	
	// checking available drones for loading
	List<Drone> getAvailableDrones();
	
	//check drone battery level for a given drone
	int getBatteryLevel (Long droneId);
	
	//update drone state
	void updateDroneState (DroneState droneState, Long droneId);
}
