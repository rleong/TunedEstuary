package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import object.Fish;

public class FishTest {

	@Test
	public void testGetH() {
		Fish fish1 = new Fish(0);
		 assertEquals(0, fish1.getHazNum());
	}
	
	@Test
	public void testSetH(){
		Fish fish2 = new Fish(0);
		fish2.setHazType(3);
		assertEquals(3, fish2.getHazNum());
	}

}
