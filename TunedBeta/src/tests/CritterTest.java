package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.Test;

import framework.GameObject;
import framework.ObjectId;
import object.Critter;
import object.LandSurface;
//---
public class CritterTest {

	
	ArrayList<GameObject> testExpected = new ArrayList<GameObject>();
	Critter tester = new Critter(50.0,50.0,ObjectId.critter,false, false, null, null, null, null, null, null);
	
	@Test
	public void getBoundsTest(){
		assertEquals(tester.getBounds(), new Rectangle(56,76,20,6));
	}
	@SuppressWarnings("deprecation")
	@Test
	public void getXTest() {
		assertEquals(tester.getX(),01,50.0);
	}
	@Test
	public void getYTest() {
		assertEquals(tester.getY(),01,50.0);
	}
	@Test
	public void setXTest() {
		tester.setX(60.0);
		assertEquals(tester.getX(),01,60.0);
	}
	@Test
	public void setYTest() {
		tester.setY(60.0);
		assertEquals(tester.getY(),01,60.0);
	}
	@Test
	public void getVelXTest() {
		assertEquals(tester.getVelX(),01,1.0);
	}
	@Test
	public void getVelYTest() {
		assertEquals(tester.getVelY(),01,1.0);
	}
	@Test
	public void setVelXTest() {
		tester.setVelX(6.0);
		assertEquals(tester.getVelX(),1,6.0);
	}
	@Test
	public void setVelYTest() {
		tester.setVelY(6.0);
		assertEquals(tester.getVelY(),01,6.0);
	}
	@Test
	public void getIdTest() {
		assertEquals(tester.getId(),ObjectId.critter);
	}

	@Test
	public void setGravityTest(){
		tester.setGravity(65.0);
		assertEquals(tester.getGravity(),01,65.0);
	}
	@Test
	public void getGravityTest(){
		assertEquals(tester.getGravity(),01,1);
	}
	@Test
	public void setFallingTest(){
		tester.setFalling(false);
		assertEquals(tester.getFalling(), false);
	}
	@Test
	public void getFallingTest(){
		assertEquals(tester.getFalling(), true);
	}
	/*@Test
	public void collisionTest1(){
		ArrayList<GameObject> test1 = new ArrayList<GameObject>();
		test1.add(tester);
		test1.add(new LandSurface(50,50,ObjectId.landSurface,null,null));
		tester.testCollision(test1);
		assertEquals(((Critter) test1.get(0)).getInWater(), false);
		
	}
	@SuppressWarnings("deprecation")
	@Test
	public void collisionTest2(){
		ArrayList<GameObject> test1 = new ArrayList<GameObject>();
		test1.add(tester);
		test1.add(new LandSurface(50,50,ObjectId.sand,null,null));
		tester.testCollision(test1);
		assertEquals(((Critter) test1.get(0)).getVelY(),01, 0);
		
	}*/

}
