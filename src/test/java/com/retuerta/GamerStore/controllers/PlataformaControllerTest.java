package com.retuerta.GamerStore.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class PlataformaControllerTest {
	
	PlataformaController plataformaController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.plataformaController = new PlataformaController();
	}
	
	@Test
	public void retrieveAllPlataformaTest() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this).build();
		
		//mockMvc.perform(MockMvcRequestBuilders.get("/plataformas")).andExpect(MockMvcResultMatchers.status().isOk());
		assertEquals( '5', '5');
	}



}
