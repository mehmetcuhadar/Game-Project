package test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import domain.game.Settings;
import domain.obstacles.Obstacle;

public class addObstaclesTest {
	static Settings settings;

	@Before
	public void setUpBeforeClass() throws Exception {
		settings = Settings.getInstance();
	}

	@Test
	public void steinsGateLiveCheck() {
		settings.addObstacles("SteinsGate");
		boolean liveRange = false;
		if (settings.livesOfFirmObstacles.get(0) <= 5 && settings.livesOfFirmObstacles.get(0) > 0) {
			liveRange = true;
		}
		assertEquals(liveRange, true);

	}

	@Test
	public void bulletCheck() {
		settings.addObstacles("Bullet");
		Obstacle bullet = settings.getBullet().get(0);
		assertNotNull(bullet);

	}

	@Test
	public void idCheckAfterAddition() {
		settings.addObstacles("PandorasBox");
		Random rand = new Random();
		int randomId = rand.nextInt();
		settings.setObstacleID(randomId, "PandorasBox");
		assertEquals(randomId, settings.expObstacles.get(0).getObstacleID());

	}

	@Test
	public void IllegalArgumentException() {
		String checkObs = "deneme";
		assertThrows(IllegalArgumentException.class, () -> {
			settings.addObstacles(checkObs);
		});

	}

	@Test
	public void NullCheck() {
		String checkObs = null;
		assertThrows(NullPointerException.class, () -> {
			settings.addObstacles(checkObs);
		});

	}

}