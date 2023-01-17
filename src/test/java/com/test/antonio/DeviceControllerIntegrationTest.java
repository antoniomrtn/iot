package com.test.antonio;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.antonio.model.Sim;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DeviceControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	 
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
    public void findDevicesByStatusWaitingForActivation() throws Exception{
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/devices")
        		.param("status", "WAITING FOR ACTIVATION"));
        response.andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[*].sim.status", Matchers.everyItem(Matchers.equalTo("WAITING FOR ACTIVATION"))));
    }
	
	@Test
    public void updateStatusOfGivenDevice() throws Exception{
		Sim sim = new Sim(1, "ACTIVE");	
		
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/devices/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sim)));
        response.andDo(MockMvcResultHandlers.print())
        .andExpect(jsonPath("$.sim.status").value(sim.getStatus()));
    }
	
	@Test
    public void updateStatusOfGivenDeviceReturn404() throws Exception{
		Sim sim = new Sim(100, "ACTIVE");	
		
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/devices/100")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sim)));
        response.andExpect(status().isNotFound()).andDo(print());
    }
	
	@Test
    public void findDevicesIsReadyTrue() throws Exception{
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/ready"));
		
		response.andDo(MockMvcResultHandlers.print())
		.andExpect(jsonPath("$[*].isReady", Matchers.everyItem(Matchers.equalTo(1))));
    }

}
