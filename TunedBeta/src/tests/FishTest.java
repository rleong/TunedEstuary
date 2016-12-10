package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import object.FishInSchool;

public class FishTest {

	@Test
	public void testGetH() {
		FishInSchool fish1 = new FishInSchool(0);
		 assertEquals(0, fish1.getHazNum());
	}
	
	@Test
	public void testSetH(){
		FishInSchool fish2 = new FishInSchool(0);
		fish2.setHazType(3);
		assertEquals(3, fish2.getHazNum());
	}

}
