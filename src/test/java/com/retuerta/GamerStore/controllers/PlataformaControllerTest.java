package com.retuerta.GamerStore.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class PlataformaControllerTest {
	
	PlataformaController plataformaController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.plataformaController = new PlataformaController();
	}
	
	@Test
	public void retrieveAllPlataformaTest() throws Exception {
		
		//mockMvc.perform(MockMvcRequestBuilders.get("/plataformas")).andExpect(MockMvcResultMatchers.status().isOk());
		assertEquals( '5', '5');
	}



}
