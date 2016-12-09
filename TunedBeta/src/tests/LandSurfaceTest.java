package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.Test;

import framework.GameObject;
import framework.ObjectId;
import object.Barrier;
import object.LandSurface;
import object.Waves;

public class LandSurfaceTest {

	LandSurface tester = new LandSurface(50.0,50.0,ObjectId.landSurface,null, null);
	
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
		assertEquals(tester.getId(),ObjectId.landSurface);
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
