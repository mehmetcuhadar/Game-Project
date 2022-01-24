package test;

import static org.junit.Assert.*;

import org.junit.Test;

import ui.BuildingEntryFrame;

public class constraintCheckTest {
	
	@Test
	public void test() {
		BuildingEntryFrame frame = new BuildingEntryFrame();
		negativeEntryTest(frame);
		maxObstacleEntryTest(frame);
		minNumOfFirmObsEntryTest(frame);
		minNumOfSimpleObsEntryTest(frame);
		trueEntryTest(frame);
		
	}
	
	public void negativeEntryTest(BuildingEntryFrame frame) {
		assertThrows(IllegalArgumentException.class, ()->
		{frame.constraintCheck(-5,10,10,10);
	});
	}
	
	public void maxObstacleEntryTest(BuildingEntryFrame frame) {
		assertFalse(frame.constraintCheck(300, 24, 13, 10));
	}
	
	public void minNumOfFirmObsEntryTest(BuildingEntryFrame frame) {
		assertFalse(frame.constraintCheck(76, 8, 13, 10));
	}
	
	public void minNumOfSimpleObsEntryTest(BuildingEntryFrame frame) {
		assertFalse(frame.constraintCheck(70, 24, 13, 10));
	}
	
	public void trueEntryTest(BuildingEntryFrame frame) {
		assertTrue(frame.constraintCheck(76, 24, 12, 10));
	}
	
	
	

}
