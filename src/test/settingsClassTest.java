package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.game.Settings;
import domain.obstacles.Obstacle;

public class settingsClassTest {
	Settings settings;
	
	@Before
	public void setUp() {
		settings = Settings.getInstance();
	}
	
	@After
	public void TearDown() {
		Settings.getInstance().simpleObstacles = new ArrayList<Obstacle>();
		Settings.getInstance().firmObstacles = new ArrayList<Obstacle>();
		Settings.getInstance().livesOfFirmObstacles = new ArrayList<Integer>();
		Settings.getInstance().giftObstacles = new ArrayList<Obstacle>();;
	}
	
	
	@Test
	public void testInsert() {
		settings.addObstacles("WallMaria");
		settings.setObstacleID(0, "WallMaria");
		settings.addObstacles("WallMaria");
		settings.setObstacleID(1, "WallMaria");
		settings.addObstacles("SteinsGate");
		settings.setObstacleID(0, "SteinsGate");
		settings.addObstacles("PandorasBox");
		settings.setObstacleID(0, "PandorasBox");
		settings.addObstacles("GiftBox");
		settings.setObstacleID(0, "GiftBox");
		assertTrue(settings.repOk(settings.simpleObstacles));
		assertTrue(settings.repOk(settings.firmObstacles));
		assertTrue(settings.repOk(settings.expObstacles));
		assertTrue(settings.repOk(settings.giftObstacles));
		
	}
	
	@Test
	public void testRemove() {
		settings.addObstacles("WallMaria");
		settings.setObstacleID(0, "WallMaria");
		settings.addObstacles("WallMaria");
		settings.setObstacleID(1, "WallMaria");
		settings.removeObs("WallMaria", 1);
		settings.removeObs("WallMaria", 0);
		assertFalse(settings.repOk(settings.simpleObstacles));
	}
	
	
	@Test
	public void testWithCombinations() {
		settings.addObstacles("WallMaria");
		settings.setObstacleID(0, "WallMaria");
		settings.addObstacles("WallMaria");
		settings.setObstacleID(1, "WallMaria");
		settings.removeObs("WallMaria", 1);
		settings.addObstacles("WallMaria");
		settings.setObstacleID(1, "WallMaria");
		assertEquals(2, settings.simpleObstacles.size());
	}
	
	
	@Test
	public void firmObstacleLiveTest() {
		settings.addObstacles("SteinsGate");
		settings.setObstacleID(0, "SteinsGate");
		settings.setLocation("SteinsGate", 0, 0);
		assertTrue(settings.repOk(settings.firmObstacles));
		int live = Settings.getInstance().getItemLive(0);
		settings.getRemoveObs(0, 0);
		if(live==1) {
			assertThrows(IndexOutOfBoundsException.class, ()->
			{Settings.getInstance().getItemLive(0);
		});
		}else {
			assertEquals(Integer.parseInt(""+live), Settings.getInstance().getItemLive(0)+1);
		}
		
	}
	
	@Test
	public void testResetList() {
		settings.addObstacles("WallMaria");
		settings.setObstacleID(0, "WallMaria");
		settings.addObstacles("WallMaria");
		settings.setObstacleID(1, "WallMaria");
		settings.addObstacles("SteinsGate");
		settings.setObstacleID(0, "SteinsGate");
		settings.addObstacles("PandorasBox");
		settings.setObstacleID(0, "PandorasBox");
		settings.addObstacles("GiftBox");
		settings.setObstacleID(0, "GiftBox");
		settings.resetObsList(settings.simpleObstacles);
		settings.resetObsList(settings.expObstacles);
		settings.resetObsList(settings.giftObstacles);
		settings.resetObsList(settings.firmObstacles);
		assertFalse(settings.repOk(settings.simpleObstacles));
		assertFalse(settings.repOk(settings.expObstacles));
		assertFalse(settings.repOk(settings.giftObstacles));
		assertFalse(settings.repOk(settings.firmObstacles));
	}

}
