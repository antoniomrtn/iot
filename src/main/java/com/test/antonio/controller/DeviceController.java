package com.test.antonio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.test.antonio.exception.ResourceNotFoundException;
import com.test.antonio.model.Device;
import com.test.antonio.model.Sim;
import com.test.antonio.service.DeviceService;


@RestController
@RequestMapping(path = "/api/v1")
public class DeviceController {
	
	@Autowired
	DeviceService deviceService;
	
	@GetMapping("/devices")
	public List<Device> findDevicesByStatus(@RequestParam("status") String status) {
        return deviceService.findDevicesByStatus(status);
    }
	
	@PostMapping("/devices/{id}")
    public Device updateStatus(@PathVariable int id, @RequestBody Sim sim) {
		try { 
			return deviceService.updateStatus(id, sim);
	    } catch (ResourceNotFoundException ex) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    }
    }
	
	@GetMapping("/ready")
    public List<Device> findReadyDevices() {
        return deviceService.findReadyDevices();
    }

}
