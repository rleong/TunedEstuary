package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import framework.ObjectId;
import object.FishInSchool;
import object.SchoolFish;

import static org.hamcrest.CoreMatchers.*;
public class SchoolFishTest {

	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetX(){
		SchoolFish school = new SchoolFish(36, 100, ObjectId.school, null, null);
		assertEquals(36, school.getX(), 0);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSetX(){
		SchoolFish school = new SchoolFish(320, 93, ObjectId.school, null, null);
		school.setX(20);
		assertEquals(20, school.getX(), 0);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetY(){
		SchoolFish school = new SchoolFish(853, 742, ObjectId.school, null, null);
		assertEquals(742, school.getY(), 0);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSetY(){
		SchoolFish school = new SchoolFish(246, 153, ObjectId.school, null, null);
		school.setY(14);
		assertEquals(14, school.getY(), 0);
	}
	
	@Test
	public void testIsDead(){
		SchoolFish school = new SchoolFish(1371, 423, ObjectId.school, null, null);
		assertEquals(false, school.isDead());
	}
	@Test
	public void testGetBounds(){
		SchoolFish school = new SchoolFish(132, 645, ObjectId.school, null, null);
		assertNull(school.getBounds());
		
	}
}
