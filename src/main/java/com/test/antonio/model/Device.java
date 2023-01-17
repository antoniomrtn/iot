package com.test.antonio.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "device")
public class Device {

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "is_ready")
	private int isReady;
	
	@Column(name = "temperature")
	private int temperature;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sim", referencedColumnName = "id")
	private Sim sim;
	
	public Device() {
		super();
	}
	public Device(int id, int isReady, int temperature, Sim sim) {
		super();
		this.id = id;
		this.isReady = isReady;
		this.temperature = temperature;
		this.sim = sim;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIsReady() {
		return isReady;
	}
	public void setIsReady(int isReady) {
		this.isReady = isReady;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public Sim getSim() {
		return sim;
	}
	public void setSim(Sim sim) {
		this.sim = sim;
	}
	
}
