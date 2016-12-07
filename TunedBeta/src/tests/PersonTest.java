package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.LinkedList;

import org.junit.Test;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import object.LandSurface;
import object.Person;
import object.Waves;

public class PersonTest {
	Person tester = new Person(10,10,ObjectId.person,null,0);
	
	@Test
	public void getBoundsTest(){
		assertEquals(tester.getBounds(), new Rectangle(10,10,32,32));
	}
	@Test
	public void getBoundsBottomTest(){
		assertEquals(tester.getBoundsBottom(), new Rectangle(16,36,20,6));
	}
	@Test
	public void getBoundsWallTest(){
		assertEquals(tester.getBoundsWall(), new Rectangle(6,-90,15,200));
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
		assertEquals(tester.getId(),ObjectId.person);
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
	public void getDirectionTest(){
		assertEquals(tester.getDirection(),0);
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
		test1.add(tester);
		test1.add(new LandSurface(10,10,ObjectId.landSurface,null));
		tester.testCollision(test1);
		Person test2 = (Person) test1.get(0);
		assertEquals(test2.getVelY(),0,0);
	}
	@Test
	public void testCollision2(){
		LinkedList<GameObject>test1 = new LinkedList<GameObject>();
		test1.add(tester);
		test1.add(new LandSurface(10.0,10.0,ObjectId.wall,null));
		tester.testCollision(test1);
		Person test2 = (Person) test1.get(0);
		assertEquals(test2.getDirection(),0,1);
	}

}
