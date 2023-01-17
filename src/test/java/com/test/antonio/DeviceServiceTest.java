package com.test.antonio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.test.antonio.model.Device;
import com.test.antonio.model.Sim;
import com.test.antonio.repository.DeviceRepository;
import com.test.antonio.repository.SimRepository;
import com.test.antonio.service.DeviceService;

class DeviceServiceTest {
	@Mock
	private DeviceRepository deviceRepository;
	
	@Mock
	private SimRepository simRepository;
	
	@InjectMocks
	private DeviceService deviceService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void findDevicesByStatusWaitingForActivation()
	{
		List<Device> list = new ArrayList<Device>();		
		list.add(new Device(1, 0, 0, new Sim(1, 2, "Italy", "WAITING FOR ACTIVATION")));
		list.add(new Device(2, 0, 0, new Sim(2, 2, "Italy", "WAITING FOR ACTIVATION")));
	
		when(deviceRepository.findBySim_Status("WAITING FOR ACTIVATION")).thenReturn(list);
		List<Device> empList = deviceService.findDevicesByStatus("WAITING FOR ACTIVATION");
		assertEquals(empList, list);
	}
	
	@Test
	public void updateStatusOfGivenDevice()
	{
		Sim sim = new Sim(1, 2, "Italy", "ACTIVE");
		Device device = new Device(1, 1, 0, sim);
		
		when(simRepository.findById(1)).thenReturn(Optional.of(sim));
		when(deviceRepository.findById(1)).thenReturn(Optional.of(device));
		
		Device updatedDevice = deviceService.updateStatus(1, new Sim(1, "ACTIVE"));
		assertEquals(updatedDevice.getSim().getStatus(), "ACTIVE");
	}
	
	
	@Test
	public void findDevicesIsReadyTrue()
	{
		List<Device> list = new ArrayList<Device>();		
		list.add(new Device(1, 1, 0, new Sim(1, 2, "Italy", "ACTIVE")));
		list.add(new Device(2, 1, 0, new Sim(2, 2, "Italy", "ACTIVE")));
		list.add(new Device(3, 1, 0, new Sim(3, 2, "Italy", "ACTIVE")));
	
		when(deviceRepository.findByIsReadyOrderById(1)).thenReturn(list);
		List<Device> empList = deviceService.findReadyDevices();
		assertEquals(3, empList.size());
	}
}
