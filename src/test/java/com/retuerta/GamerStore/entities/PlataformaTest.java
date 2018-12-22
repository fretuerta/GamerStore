package com.retuerta.GamerStore.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PlataformaTest {

	Plataforma plataforma;
	
	@Before
	public void setUp() {
		plataforma = new Plataforma();
	}
	
	@Test
	public void getId() throws Exception {
		Long idValue = 4l;
		plataforma.setId(idValue);
		assertEquals( idValue, plataforma.getId());
	}
}
