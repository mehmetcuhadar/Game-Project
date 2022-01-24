package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import domain.game.NeedForSpearGame;
import domain.playerObjects.EnchantedSphere;
import domain.playerObjects.NoblePhantasm;

public class RunningModeFrame extends JFrame {
	private static final Color BACKGROUND_COLOR = new Color(140, 140, 140);

	JPanel infoPanel;
	RunningPanel runningPanel;
	RunningFrameTopPanel runningFrameTopPanel;
	JLabel scoreLabel = new JLabel("Score: ");
	JLabel livesLabel = new JLabel("Lives: ");
	NeedForSpearGame game=NeedForSpearGame.getInstance();
	GridBagConstraints gbc;

	JButton saveButton;
	JButton loadButton;
	JButton pauseButton;
	JButton quitButton;
	
	final int PANEL_WIDTH = 1250;
	final int PANEL_HEIGHT = 700;
	
	final int PADDLE_WIDTH = PANEL_WIDTH / 10;
	final int PADDLE_HEIGHT = 20;
	
	final int BALL_WIDTH = 16;
	final int BALL_HEIGHT = 16;
	
	private int L;
	
	Image ball;
	Image paddle;
	
	
	String imgPath_enchantedSphere = "src/images/enchanted_sphere.png";
	String imgPath_noblePhantasm = "src/images/noble_phantasm.png";
	String imgPath_unstoppableSphere = "src/images/unstoppable_sphere.png";
	
	
	EnchantedSphere myBall;
	NoblePhantasm myPaddle;
	
	
	Timer timer;
	
	boolean isKeyPressed = false;
	
	int ball_x = 150;
	int ball_y = 300;
	
	int ballxVelocity = 10;
	int ballyVelocity = 11;
	
	int paddle_x = (PANEL_WIDTH / 2) - (PADDLE_WIDTH / 2);
	int paddle_y = PANEL_HEIGHT - PANEL_HEIGHT / 10 - 90;
	
	
	

	public RunningModeFrame() throws IOException {
		this.L= this.PANEL_WIDTH/10;
		setBounds(100, 50, 1250, 750);
		if(game.getSphereIsUnstoppable()==true) {
			ball = new ImageIcon(imgPath_unstoppableSphere).getImage();
			UIController.getInstance().initiliazeSphere(ball_x, ball_y, BALL_WIDTH*2, BALL_HEIGHT*2, ball);
		}else {
			ball = new ImageIcon(imgPath_enchantedSphere).getImage();
			UIController.getInstance().initiliazeSphere(ball_x, ball_y, BALL_WIDTH, BALL_HEIGHT, ball);
		}
		
		
		paddle = new ImageIcon(imgPath_noblePhantasm).getImage();
		if(game.getNoblePhantasmIsExpanded()==true) {
			UIController.getInstance().initializeNoblePhantasm(paddle_x, paddle_y, L*2, 20, paddle);
		}else {
			UIController.getInstance().initializeNoblePhantasm(paddle_x, paddle_y, L, 20, paddle);
		}
		
		runningPanel = new RunningPanel();
		runningFrameTopPanel = new RunningFrameTopPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(runningFrameTopPanel,BorderLayout.NORTH);
		add(runningPanel,BorderLayout.CENTER);
		setVisible(true);
		

	}
	public static void main(String[] args) throws IOException {
		new RunningModeFrame();
	}
	


}
