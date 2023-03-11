package com.example.drones.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.drones.model.DroneModel;
import com.example.drones.model.DroneState;

@Entity
public class Drone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String serial;
	private DroneModel model;
	private int weigthLimit;
	private int batteryCapacity;
	private DroneState state;

	public Drone() {
	};

	public Drone(String serial, DroneModel model, int weightLimit, int batteryCapacity, DroneState state) {
		super();
		this.serial = serial;
		this.model = model;
		this.weigthLimit = weightLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public DroneModel getModel() {
		return model;
	}

	public void setModel(DroneModel model) {
		this.model = model;
	}

	public int getWeigthLimit() {
		return weigthLimit;
	}

	public void setWeigthLimit(int weigthLimit) {
		this.weigthLimit = weigthLimit;
	}

	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public DroneState getState() {
		return state;
	}

	public void setState(DroneState state) {
		this.state = state;
	}

}
