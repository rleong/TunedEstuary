package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;

import framework.ObjectId;
import object.Tree;

public class TreeTest {
	Tree tester = new Tree(345, 45, ObjectId.tree, 0, null, null);
	@SuppressWarnings("deprecation")
	@Test
	public void testGetX() {
		Tree testTree = new Tree(345, 45, ObjectId.tree, 0, null, null);
		assertEquals(345, testTree.getX(), 0);
	}
	@Test
	public void testGetStage() {
		Tree testTree = new Tree(345, 45, ObjectId.tree, 0, null, null);
		assertEquals(345, testTree.getStage(), 345);
	}
	@Test
	public void testSetStage() {
		Tree testTree = new Tree(345, 45, ObjectId.tree, 0, null, null);
		testTree.setStage(100);
		assertEquals(testTree.getStage(),100);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSetX() {
		Tree testTree = new Tree(345, 45, ObjectId.tree, 0, null, null);
		testTree.setX(32);
		assertEquals(32, testTree.getX(),0);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetY() {
		Tree testTree = new Tree(423, 534, ObjectId.tree, 0, null, null);
		assertEquals(534, testTree.getY(), 0);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testSetY() {
		Tree testTree = new Tree(654, 432, ObjectId.tree, 0, null, null);
		testTree.setY(1);
		assertEquals(1, testTree.getY(),0);
	}
	
	@Test
	public void testBounds(){
		Tree testTree = new Tree(123, 54, ObjectId.tree, 0, null, null);
		Rectangle testRect = new Rectangle(107, 22, 64, 64);
		assertEquals(testRect, testTree.getBounds());
	}
	@Test
	public void getXTest() {
		assertEquals(tester.getX(),01,345);
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
		assertEquals(tester.getId(),ObjectId.tree);
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
