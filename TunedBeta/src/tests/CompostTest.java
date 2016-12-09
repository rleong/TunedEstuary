package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;

import framework.ObjectId;
import object.Compost;
import object.LandSurface;

public class CompostTest {

	Compost tester = new Compost(50.0,50.0,ObjectId.compost,null, 0, null);
	
	@Test
	public void getBoundsTest(){
		assertEquals(tester.getBounds(), new Rectangle(50,50,32,32));
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
		tester.setVelX(1);
		assertEquals(tester.getVelX(),01,1.0);
	}
	@Test
	public void getVelYTest() {
		tester.setVelY(1);
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
		assertEquals(tester.getId(),ObjectId.compost);
	}
	@Test
	public void getIdTest2() {
		LandSurface tester2 = new LandSurface(50.0,50.0,ObjectId.sand,null, null);
		assertEquals(tester2.getId(),ObjectId.sand);
	}
	@Test
	public void getIdTest3() {
		LandSurface tester3 = new LandSurface(50.0,50.0,ObjectId.waterSurface,null, null);
		assertEquals(tester3.getId(),ObjectId.waterSurface);
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
}
