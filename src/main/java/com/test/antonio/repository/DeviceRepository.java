package com.test.antonio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.antonio.model.Device;


@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

	public List<Device> findBySim_Status(String status);
	
	public List<Device> findByIsReadyOrderById(int isReady);
	
}
