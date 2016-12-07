package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.LinkedList;

import org.junit.Test;

import framework.GameObject;
import framework.ObjectId;
import object.Gabion;
import object.Waves;

public class GabionTest {
	Gabion tester = new Gabion(50.0,50.0,ObjectId.gabion,null);
	
	@Test
	public void getBoundsTest(){
		assertEquals(tester.getBounds(), new Rectangle(50,50,45,45));
	}
	@SuppressWarnings("deprecation")
	@Test
	public void getXTest() {
		assertEquals(tester.getX(),01,50.0);
	}
	@Test
	public void getHpTest(){
		assertEquals(tester.getHp(),3);
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
		assertEquals(tester.getId(),ObjectId.gabion);
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
		test1.add(new Waves(50.0,50.0,ObjectId.waves,null));
		testexpected.add(tester);
		assertEquals(tester.testCollision(test1),testexpected);	
	}
	@Test
	public void testCollision2(){
		LinkedList<GameObject>test1 = new LinkedList<GameObject>();
		test1.add(tester);
		test1.add(new Waves(50.0,50.0,ObjectId.waves,null));
		tester.testCollision(test1);
		assertEquals(tester.getHp(),2);	
	}
}