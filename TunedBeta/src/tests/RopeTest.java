package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.LinkedList;

import org.junit.Test;

import framework.GameObject;
import framework.ObjectId;
import object.LandSurface;
import object.Rope;
import object.Wood;

public class RopeTest {

	LinkedList<GameObject>test1 = new LinkedList<GameObject>();
	Rope tester = new Rope(50,50,ObjectId.rope,null);
	
	@Test
	public void getBoundsTest(){
		assertEquals(tester.getBounds(), new Rectangle(34,34,16,16));
	}
	@Test
	public void getBoundsFallTest(){
		assertEquals(tester.getBoundsFall(), new Rectangle(34,34,32,32));
	}
	@Test
	public void getBoundsBottomTest(){
		assertEquals(tester.getBoundsBottom(), new Rectangle(56,76,20,6));
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
		assertEquals(tester.getId(),ObjectId.rope);
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
	@Test
	public void testCollision(){
		test1.add(tester);
		test1.add(new LandSurface(50.0,50.0,ObjectId.landSurface,null));
		tester.testCollision(test1);
		assertEquals(test1.get(0).getY(),01,18.0);	
	}
}
