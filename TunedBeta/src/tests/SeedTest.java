package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;

import framework.ObjectId;
import object.Seed;

public class SeedTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testGetX() {
		Seed testSeed = new Seed(140, 635, ObjectId.seed, null, 0, null);
		assertEquals(140, testSeed.getX(), 0);
	}

	@Test
	public void testSetX() {
		Seed testSeed = new Seed(123, 635, ObjectId.seed, null, 0, null);
		testSeed.setX(12);
		assertEquals(12, testSeed.getX(), 0);
	}
	
	@Test
	public void testGetY() {
		Seed testSeed = new Seed(53, 543, ObjectId.seed, null, 0, null);
		assertEquals(543, testSeed.getY(), 0);
	}

	@Test
	public void testSetY() {
		Seed testSeed = new Seed(143, 754, ObjectId.seed, null,0, null);
		testSeed.setY(43);
		assertEquals(43, testSeed.getY(), 0);
	}
	
	@Test
	public void testGetBounds() {
		Seed testSeed = new Seed(132, 75, ObjectId.seed, null,0,  null);
		Rectangle testRect = new Rectangle(132, 75, 32, 32);
		assertEquals(testRect, testSeed.getBounds());
	}
	
	@Test
	public void testCollision(){
		Seed testSeed = new Seed(123, 854, ObjectId.seed, null,0, null);
		//assertEquals(true, collision());
	}
	
}
