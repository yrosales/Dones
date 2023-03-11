package com.example.drones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.drones.entity.Drone;
import com.example.drones.repository.DroneRepository;

@Service

public class DroneService implements DroneServiceInterface {
	
	@Autowired
    private DroneRepository droneRepository;

	public Drone saveDrone(Drone drone) {
		return droneRepository.save(drone);
	}

	public List<Drone> fetchDroneList() {
		return (List<Drone>) droneRepository.findAll();
	}

	public Drone updateDrone(Drone newDrone, Long newDroneId) {
		Drone drone = droneRepository.findById(newDroneId).get();
		drone.setSerial(newDrone.getSerial());
		drone.setModel(newDrone.getModel());
		drone.setWeigthLimit(newDrone.getWeigthLimit());
		drone.setBatteryCapacity(newDrone.getBatteryCapacity());
		drone.setState(newDrone.getState());
		return droneRepository.save(drone);
	}

	public void deleteDroneById(Long droneId) {
		droneRepository.deleteById(droneId);
	}

}
