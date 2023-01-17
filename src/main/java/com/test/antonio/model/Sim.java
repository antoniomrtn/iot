package com.test.antonio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sim")
public class Sim {

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "operator_code")
	private int operatorCode;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "status")
	private String status;
	
	public Sim() {
		super();
	}
	
	public Sim(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	
	public Sim(int id, int operatorCode, String country, String status) {
		super();
		this.id = id;
		this.operatorCode = operatorCode;
		this.country = country;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(int operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
