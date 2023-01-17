package com.test.antonio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.antonio.exception.ResourceNotFoundException;
import com.test.antonio.model.Device;
import com.test.antonio.model.Sim;
import com.test.antonio.repository.DeviceRepository;
import com.test.antonio.repository.SimRepository;


@Service
public class DeviceService {
	
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private SimRepository simRepository;

	public List<Device> findDevicesByStatus(String status) {
		return deviceRepository.findBySim_Status(status);
	}
	
	public Device updateStatus(int id, Sim sim) {
		Sim simDb = simRepository.findById(sim.getId()).orElseThrow(() -> new ResourceNotFoundException());
		Device deviceDb = deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		if (checkSetIsReady(sim.getStatus(), deviceDb)) {
			deviceDb.setIsReady(1);
			deviceRepository.save(deviceDb);	
		} else if (checkSetIsNotReady(sim.getStatus(), deviceDb)) {
			deviceDb.setIsReady(0);
			deviceRepository.save(deviceDb);
		}
		simDb.setStatus(sim.getStatus());
		simRepository.save(simDb);
		return deviceDb;
	}
	
	private boolean checkSetIsReady(String newStatus, Device device) {
		return !"ACTIVE".equals(device.getSim().getStatus()) && "ACTIVE".equals(newStatus)
				&& device.getTemperature() <= 85 && device.getTemperature() >= -25;
	}
	
	private boolean checkSetIsNotReady(String newStatus, Device device) {
		return "ACTIVE".equals(device.getSim().getStatus()) && !"ACTIVE".equals(newStatus);
	}

	public List<Device> findReadyDevices() {
		return deviceRepository.findByIsReadyOrderById(1);
	}
}
