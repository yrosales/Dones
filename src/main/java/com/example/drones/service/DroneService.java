package com.example.drones.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.drones.entity.Drone;
import com.example.drones.entity.Medication;
import com.example.drones.model.DroneState;
import com.example.drones.repository.DroneRepository;
import com.example.drones.repository.MedicationRepository;

@Service

public class DroneService implements DroneServiceInterface {

	@Autowired
	private DroneRepository droneRepository;

	@Autowired
	private MedicationRepository medicationRepository;
	
	@Override
	public Drone saveDrone(Drone drone) {
		return droneRepository.save(drone);
	}

	@Override
	public List<Drone> fetchDroneList() {
		return (List<Drone>) droneRepository.findAll();
	}

	@Override
	public Drone updateDrone(Drone drone, Long droneId) {
		return droneRepository.findById(droneId).map(actDrone -> {
			actDrone.setSerial(drone.getSerial());
			actDrone.setModel(drone.getModel());
			actDrone.setWeigthLimit(drone.getWeigthLimit());
			actDrone.setBatteryCapacity(drone.getBatteryCapacity());
			actDrone.setState(drone.getState());
			actDrone.setMedications(drone.getMedications());
			return droneRepository.save(actDrone);
		}).orElseGet(() -> {
			return droneRepository.save(drone);
		});
	}

	@Override
	public void deleteDroneById(Long droneId) {
		droneRepository.deleteById(droneId);
	}

	@Override
	public Drone loadMedication(Long droneId, Long medicationId) {
		Drone drone = droneRepository.findById(droneId).get();
		Medication medication = medicationRepository.findById(medicationId).get();
		//prevent overload
		if (!overload(drone,medication)) {
			drone.getMedications().add(medication);
		}
		return updateDrone(drone, droneId);
	}

	@Override
	public List<Medication> getLoadedMedications(Long droneId) {
		Drone drone = droneRepository.findById(droneId).get();
		return new ArrayList<Medication>(drone.getMedications());
	}

	@Override
	public List<Drone> getAvailableDrones() {
		List<Drone> drones = fetchDroneList();
		List<Drone> availableDrones = new ArrayList<>();
		for (Drone drone : drones) {
			if (drone.getState().equals(DroneState.IDLE)) {
				availableDrones.add(drone);
			}
		}
		return null;
	}

	@Override
	public int getBatteryLevel(Long droneId) {
		Drone drone = droneRepository.findById(droneId).get();
		return drone.getBatteryCapacity();
	}

	/**
	 * check if medication can be loaded
	 * @param drone
	 * @param medication
	 * @return true if the load does not exceed the drone's weight limit
	 */
	private boolean overload(Drone drone, Medication medication) {
		int loadedWeigth = 0;
		for (Medication loadMedication : drone.getMedications()) {
			loadedWeigth += loadMedication.getWeight();
		}
		return loadedWeigth > drone.getWeigthLimit();
	}
}
