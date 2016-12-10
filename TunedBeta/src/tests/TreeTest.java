package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;

import framework.ObjectId;
import object.Tree;

public class TreeTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testGetX() {
		Tree testTree = new Tree(345, 45, ObjectId.tree, 0, null, null);
		assertEquals(345, testTree.getX(), 0);
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
		Rectangle testRect = new Rectangle(123, 54 - 96, 32, 128);
		assertEquals(testRect, testTree.getBounds());
	}
}
