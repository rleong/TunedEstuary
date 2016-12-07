package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.LinkedList;

import org.junit.Test;

import framework.GameObject;
import framework.ObjectId;
import object.Barrier;
import object.Waste;
import object.WasteBin;
import object.Waves;

public class WasteBinTest {

	WasteBin tester = new WasteBin(50.0,50.0,ObjectId.wasteBin,0, null, null);
	
	@Test
	public void getBoundsTest(){
		assertEquals(tester.getBounds(), new Rectangle(50,50,32,32));
	}
	@Test
	public void getTypeTest(){
		assertEquals(tester.getType(),0);
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
		assertEquals(tester.getId(),ObjectId.wasteBin);
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
		LinkedList<GameObject>test1 = new LinkedList<GameObject>();
		LinkedList<GameObject>testexpected = new LinkedList<GameObject>();
		test1.add(tester);
		test1.add(new Waste(50.0,50.0,ObjectId.waste,null, null, null, null, 0));
		testexpected.add(tester);
		assertEquals(tester.testCollision(test1),testexpected);	
	}
}
