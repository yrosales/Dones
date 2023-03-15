package com.example.drones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.drones.entity.Drone;
import com.example.drones.entity.Medication;
import com.example.drones.exception.LowLevelException;
import com.example.drones.exception.OverLoadException;
import com.example.drones.model.DroneState;
import com.example.drones.model.StateRequest;
import com.example.drones.service.DroneService;

@RestController
public class DroneController {

	@Autowired
	private DroneService droneService;

	@PostMapping("/drones")
	public Drone saveDrone(@RequestBody Drone drone) {
		return droneService.saveDrone(drone);
	}

	@GetMapping("/drones")
	public List<Drone> fetchDroneList() {
		return droneService.fetchDroneList();
	}
	
	@GetMapping("/drones/battery-level/{id}")
	public int getBatteryLevel(@PathVariable("id") Long droneId) {
		return droneService.getBatteryLevel(droneId);
	}
	
	@GetMapping("/drones/availables")
	public List<Drone> fetchAvailableDroneList() {
		return droneService.getAvailableDrones();
	}
	
	@GetMapping("/drones/medications/{id}")
	public List<Medication> fetchDroneMedicatiosnList(@PathVariable("id") Long droneId) {
		return droneService.getLoadedMedications(droneId);
	}

	@PutMapping("/drones/{id}")
	public Drone updateDrone(@RequestBody Drone drone, @PathVariable("id") Long droneId) {
		return droneService.updateDrone(drone, droneId);
	}
	
	@PutMapping("/drones/load/{droneId}/{medicatioId}")
	public Drone loadMedication( @PathVariable("droneId") Long droneId, @PathVariable("medicatioId") Long medicationId) {
		Drone drone = droneService.loadMedication(droneId, medicationId);
		if  (drone!=null) {
			return drone;
		} else throw new OverLoadException(droneId);
	}

	@DeleteMapping("/drones/{id}")
	public String deleteDroneById(@PathVariable("id") Long droneId) {
		droneService.deleteDroneById(droneId);
		return "Deleted Successfully";
	}
	
	@PutMapping("/drones/state/{id}")
	public Drone updateDroneState(@RequestBody StateRequest droneState, @PathVariable("id") Long droneId) {
		Drone drone = droneService.updateDroneState(DroneState.valueOf(droneState.getState()), droneId);
		if  (drone!=null) {
			return drone;
		} else throw new LowLevelException(droneId);
	}
}
