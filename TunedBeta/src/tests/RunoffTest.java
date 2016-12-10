package tests;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import org.junit.Test;

import framework.ObjectId;
import object.Runoff;

public class RunoffTest {
	Runoff tester = new Runoff(0, 64, null, ObjectId.runOff, 0, null, null);
	@Test
	public void getBoundsTest(){
		assertEquals(tester.getBounds(),new Rectangle(0,0,32,32));
	}
}
