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

	/**
	 * save drone in bd
	 */
	@Override
	public Drone saveDrone(Drone drone) {
		return droneRepository.save(drone);
	}

	/**
	 * get all drones list
	 * 
	 * @return drones
	 */
	@Override
	public List<Drone> fetchDroneList() {
		return (List<Drone>) droneRepository.findAll();
	}

	/**
	 * update drone data
	 * 
	 * @param         new drone data
	 * @param droneId drone to update
	 * @return update drone
	 */
	@Override
	public Drone updateDrone(Drone drone, Long droneId) {
		return droneRepository.findById(droneId).map(actDrone -> {
			actDrone.setSerial(drone.getSerial() != null ? drone.getSerial() : actDrone.getSerial());
			actDrone.setModel(drone.getModel() != null ? drone.getModel() : actDrone.getModel());
			actDrone.setWeigthLimit(
					drone.getWeigthLimit() != null ? drone.getWeigthLimit() : actDrone.getWeigthLimit());
			actDrone.setBatteryCapacity(
					drone.getBatteryCapacity() != null ? drone.getBatteryCapacity() : actDrone.getBatteryCapacity());
			actDrone.setState(drone.getState() != null ? drone.getState() : actDrone.getState());
			return droneRepository.save(actDrone);
		}).orElseGet(() -> {
			return droneRepository.save(drone);
		});
	}

	@Override
	public void deleteDroneById(Long droneId) {
		droneRepository.deleteById(droneId);
	}

	/**
	 * load a medication on a drone
	 * 
	 * @param droneId
	 * @param medicationId
	 * @return update drone
	 */
	@Override
	public Drone loadMedication(Long droneId, Long medicationId) {
		Drone drone = droneRepository.findById(droneId).get();
		Medication medication = medicationRepository.findById(medicationId).get();
		// prevent overload
		if (!overload(drone, medication)) {
			drone.setState(DroneState.LOADING);
			drone.getMedications().add(medication);
			return droneRepository.save(drone);
		} else {
			return null;
		}
		
	}

	/**
	 * get medications loaded in a drone
	 * 
	 * @param droneId
	 * @return medications loaded or null if there is not
	 */
	@Override
	public List<Medication> getLoadedMedications(Long droneId) {
		Drone drone = droneRepository.findById(droneId).get();
		return new ArrayList<Medication>(drone.getMedications());
	}

	/**
	 * get the available drones to load with medications
	 * 
	 * @return available drones
	 */
	@Override
	public List<Drone> getAvailableDrones() {
		List<Drone> drones = fetchDroneList();
		List<Drone> availableDrones = new ArrayList<>();
		for (Drone drone : drones) {
			if (drone.getState().equals(DroneState.IDLE)) {
				availableDrones.add(drone);
			}
		}
		return availableDrones;
	}

	/**
	 * get the drone´s battery level
	 * 
	 * @param droneId
	 * @return battery level
	 */
	@Override
	public int getBatteryLevel(Long droneId) {
		Drone drone = droneRepository.findById(droneId).get();
		return drone.getBatteryCapacity();
	}

	/**
	 * check if medication can be loaded
	 * 
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

	/**
	 * update the drone´s state
	 * 
	 * @param droneState
	 * @param droneId
	 */
	@Override
	public Drone updateDroneState(DroneState droneState, Long droneId) {
		Drone drone = droneRepository.findById(droneId).get();
		if (droneState == DroneState.LOADING && lowLevel(drone) ){
			return null;
		} else {
			drone.setState(droneState);
			return droneRepository.save(drone);
		}
	}
	
	/**
	 * check if drone have low battery charge (<25%)
	 * 
	 * @param drone
	 * @param medication
	 * @return true if the load does not exceed the drone's weight limit
	 */
	private boolean lowLevel(Drone drone) {
		return drone.getBatteryCapacity() < 25;
	}
}
