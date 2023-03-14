package com.example.drones;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.drones.entity.Drone;
import com.example.drones.entity.Medication;
import com.example.drones.model.DroneModel;
import com.example.drones.model.DroneState;
import com.example.drones.repository.DroneRepository;
import com.example.drones.repository.MedicationRepository;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(MedicationRepository medicationRepository, DroneRepository droneRepository) {

		// create medications
		List<Medication> medications = new ArrayList<Medication>();
		medications.add(new Medication("Medication 1", 10, "CODE_M_1"));
		medications.add(new Medication("Medication 2", 20, "CODE_M_2"));
		medications.add(new Medication("Medication 3", 50, "CODE_M_3"));
		medications.add(new Medication("Medication 4", 35, "CODE_M_4"));
		medications.add(new Medication("Medication 5", 100, "CODE_M_5"));
		medications.add(new Medication("Medication 6", 50, "CODE_M_6"));
		medications.add(new Medication("Medication 7", 20, "CODE_M_7"));
		medications.add(new Medication("Medication 8", 100, "CODE_M_8"));
		medications.add(new Medication("Medication 9", 30, "CODE_M_9"));
		medications.add(new Medication("Medication 10", 25, "CODE_M_10"));
		medications.add(new Medication("Medication 11", 45, "CODE_M_11"));
		medications.add(new Medication("Medication 12", 15, "CODE_M_12"));
		medications.add(new Medication("Medication 13", 55, "CODE_M_13"));
		medications.add(new Medication("Medication 14", 80, "CODE_M_14"));
		medications.add(new Medication("Medication 15", 40, "CODE_M_15"));
		medications.add(new Medication("Medication 16", 45, "CODE_M_16"));
		medications.add(new Medication("Medication 17", 80, "CODE_M_17"));
		medications.add(new Medication("Medication 18", 65, "CODE_M_18"));
		medications.add(new Medication("Medication 19", 65, "CODE_M_19"));
		medications.add(new Medication("Medication 20", 65, "CODE_M_20"));
		
		//create drones (10)
		List<Drone> drones = new ArrayList<Drone>();
		drones.add(new Drone("102021", DroneModel.Cruiserweight, 150, 100, DroneState.LOADING));
		drones.add(new Drone("102021", DroneModel.Lightweight, 100, 100, DroneState.IDLE));
		drones.add(new Drone("102021", DroneModel.Heavyweight, 100, 100, DroneState.IDLE));
		drones.add(new Drone("102021", DroneModel.Lightweight, 100, 100, DroneState.IDLE));
		drones.add(new Drone("102021", DroneModel.Middleweight, 100, 100, DroneState.IDLE));
		drones.add(new Drone("102021", DroneModel.Heavyweight, 100, 100, DroneState.IDLE));
		drones.add(new Drone("102021", DroneModel.Middleweight, 100, 100, DroneState.IDLE));
		drones.add(new Drone("102021", DroneModel.Lightweight, 100, 100, DroneState.IDLE));
		drones.add(new Drone("102021", DroneModel.Middleweight, 100, 100, DroneState.IDLE));
		drones.add(new Drone("102021", DroneModel.Heavyweight, 100, 100, DroneState.IDLE));
		
		List<Medication> droneMedicatiosn = new ArrayList<>();
		droneMedicatiosn.add(medications.get(0));
		droneMedicatiosn.add(medications.get(2));
		droneMedicatiosn.add(medications.get(4));
		
		drones.get(0).setMedications(droneMedicatiosn);

		// load in example database
		return args -> {

			for (Medication medication : medications) {
				log.info("Preloading " + medicationRepository.save(medication));
			}
			
			for (Drone drone : drones) {
				log.info("Preloading " + droneRepository.save(drone));
			}

		};
	}
}