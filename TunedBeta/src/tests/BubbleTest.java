package tests;

import static org.junit.Assert.*;
//0
import java.awt.Rectangle;
import java.util.LinkedList;

import org.junit.Test;

import framework.GameObject;
import framework.ObjectId;
import object.Bubble;

public class BubbleTest {

	Bubble tester = new Bubble(50.0,50.0,ObjectId.bubble,null,true);
	
	@Test
	public void getBoundsTest(){
		assertEquals(tester.getBounds(), new Rectangle(50,50,48,48));
	}
	@Test
	public void getPopTest(){
		assertEquals(tester.getPop(),false);
	}
	@Test
	public void getDeathTest(){
		assertEquals(tester.getDeath(),false);
	}
	@Test
	public void getDeath2Test(){
		tester.setX(800);
		assertEquals(tester.getDeath(),true);
	}
	@Test
	public void PopTest(){
		tester.popBubble();
		assertEquals(tester.getPop(),true);
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
		assertEquals(tester.getId(),ObjectId.bubble);
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
