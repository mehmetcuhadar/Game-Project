package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import domain.game.NeedForSpearGame;
import ui.RunningModeFrame;
import ui.RunningPanel;

public class createBallTest {
	static RunningPanel panel;
	static NeedForSpearGame game;
	static RunningModeFrame frame;

	@Before
	public void setUpBeforeClass() throws Exception {
		frame = new RunningModeFrame();
		frame.dispose();
		panel = new RunningPanel();
		game = NeedForSpearGame.getInstance();
	}

	@Test
	public void ballTest() {
		// checks the dimensions and locations of the ball truly set in the domain
		panel.createBall(false);
		assertEquals(panel.getBallImg().getHeight(), game.getSphereHeight());
		assertEquals(panel.getBallImg().getWidth(), game.getSphereWidth());
		assertEquals(panel.getBallImg().getX(), game.getXSphere());
		assertEquals(panel.getBallImg().getY(), game.getYSphere());

	}

	@Test
	public void ballVelocityTest() {
		// checks if the velocity of the ball truly set in the domain.
		panel.createBall(false);
		if (game.getDoubleAccel() == false) {
			assertEquals(game.getSphereXVelocity(), 15);
			assertEquals(game.getSphereYVelocity(), 15);
		}

	}

	@Test
	public void ballImgPathTest() {
		// checks if the velocity of the ball truly set in the domain.
		panel.setImgPath_enchantedSphere(null);
		assertThrows(NullPointerException.class, () -> {
			panel.createBall(false);
		});

	}

	@Test
	public void unstopableBallTest() {
		// checks if the dimensions and locations of the unstoppable ball truly set in
		// the domain
		panel.createBall(true);
		assertEquals(panel.getBallImg().getHeight(), game.getSphereHeight());
		assertEquals(panel.getBallImg().getWidth(), game.getSphereWidth());
		assertEquals(panel.getBallImg().getX(), game.getXSphere());
		assertEquals(panel.getBallImg().getY(), game.getYSphere());
	}

	@Test
	public void unstopableBallVelocityTest() {
		// checks if the velocity of the unstoppable ball truly set in the domain.
		panel.createBall(true);
		if (game.getDoubleAccel() == false) {
			assertEquals(game.getSphereXVelocity(), 15);
			assertEquals(game.getSphereYVelocity(), 15);
		}

	}

}
