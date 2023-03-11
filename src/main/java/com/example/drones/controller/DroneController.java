package com.example.drones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.drones.entity.Drone;
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

	@PutMapping("/drones/{id}")
	public Drone updateDrone(@RequestBody Drone drone, @PathVariable("id") Long droneId) {
		return droneService.updateDrone(drone, droneId);
	}

	@DeleteMapping("/drones/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long droneId) {
		droneService.deleteDroneById(droneId);
		return "Deleted Successfully";
	}
}
