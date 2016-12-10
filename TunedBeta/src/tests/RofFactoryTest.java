package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import framework.ObjectId;
import object.RofFactory;

public class RofFactoryTest {
	RofFactory rf1 = new RofFactory(0, 0, ObjectId.RofFactory, null, null);
	RofFactory rf2	= new RofFactory(0, 0, ObjectId.RofFactory, null, null);
	@Test
	public void RofFactoryTest(){
		assertEquals(rf1.getId(),ObjectId.RofFactory);
	}
}
