package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.LinkedList;

import org.junit.Test;

import control.Game;
import framework.GameObject;
import framework.ObjectId;
import object.Habitat;
import object.Waste;

public class HabitatTest {


	Habitat tester = new Habitat(50.0,50.0,ObjectId.habitat,null,Game.dm,null);
	
	@Test
	public void getBoundsTest(){
		assertEquals(tester.getBounds(), new Rectangle(56,76,(int)tester.getWidth(),64));
	}
	@Test
	public void getWidthTest(){
		assertEquals(tester.getWidth(),01,Game.dm.getWidth()*3/2-Game.dm.getWidth()*.84);
	}
	@Test
	public void getHealthTest(){
		assertEquals(tester.getHealth(),01,30);
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
		assertEquals(tester.getId(),ObjectId.habitat);
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
