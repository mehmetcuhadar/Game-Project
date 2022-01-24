package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Timer;

public class RunningPanel extends JPanel implements KeyListener {

	final int PANEL_WIDTH = 1250;
	final int PANEL_HEIGHT = 700;

	private int L;
	int bombCounter = 0;
	int laserCounter = 0;
	int ms = 50;
	Timer timer = new Timer();
	Image ball;
	Image paddle;
	int chanceCounter = 0;
	int magicalCounter = 0;
	int nobleCounter = 0;
	int sphereCounter = 0;
	int time = 0;
	int bulletCounter = 0;
	Graphics2D g2d;
	Polygon polyPaddle;
	Rectangle rect2;

	String imgPath_enchantedSphere = "src/images/enchanted_sphere.png";
	String imgPath_noblePhantasm = "src/images/noble_phantasm.png";

	boolean isKeyPressed = false;
	boolean laserCondition = false;
	boolean isLeft = true;
	boolean checkKeyCollision = false;
	boolean control = false;
	int paddle_y = PANEL_HEIGHT - PANEL_HEIGHT / 10;

	JLabel ballImg;
	ArrayList<JLabel> firmList = new ArrayList<JLabel>();
	ArrayList<JLabel> simpleList = new ArrayList<JLabel>();
	ArrayList<JLabel> explosiveList = new ArrayList<JLabel>();
	ArrayList<JLabel> giftList = new ArrayList<JLabel>();
	ArrayList<JLabel> bulletList = new ArrayList<JLabel>();
	ArrayList<JLabel> laserList = new ArrayList<JLabel>();
	ArrayList<JLabel> chanceGiving = new ArrayList<JLabel>();
	ArrayList<JLabel> magicalHex = new ArrayList<JLabel>();
	ArrayList<JLabel> unstoppableSphere = new ArrayList<JLabel>();
	ArrayList<JLabel> paddleExpansion = new ArrayList<JLabel>();
	ArrayList<JLabel> hollowPurple = new ArrayList<JLabel>();

	public RunningPanel() throws IOException {
		displayRunningPanel();
	}

	public void displayRunningPanel() throws IOException {

		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.L = (PANEL_WIDTH * 10) / 100;
		this.setLayout(null);
		createBall(UIController.getInstance().getSphereIsUnstoppable());
		placeObstacles("WallMaria");
		placeObstacles("SteinsGate");
		placeObstacles("PandorasBox");
		placeObstacles("GiftBox");
		startSimulation();

	}

	public void startSimulation() {
		TimerTask x = new TimerTask() {
			@Override
			public void run() {
				if (UIController.getInstance().getGameStopped() == false) {
					time = UIController.getInstance().getTime();
					time++;
					UIController.getInstance().setTime(time);
					try {
						arrangeUIComponents();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		timer.schedule(x, 1000, ms);
	}

	public void createBall(boolean isUnstopable) {
		// requires isUnstopable as True or False.
		// modifies the sphere in the UI using data from domain.
		// effects location and dimension of the sphere image in the UI.
		if (imgPath_enchantedSphere != null) {
			ball = new ImageIcon(imgPath_enchantedSphere).getImage();
		} else {
			throw new NullPointerException();
		}

		if (isUnstopable) {
			ball = new ImageIcon("src/images/unstoppable_sphere.png").getImage();
		}

		ImageIcon ballIcon = new ImageIcon(ball);
		ballImg = new JLabel(ballIcon);
		int ballWidth = UIController.getInstance().getSphereWidth();
		int ballHeight = UIController.getInstance().getSphereHeight();
		ballImg.setSize(ballWidth, ballHeight);
		int ballX = UIController.getInstance().getXSphere();
		int ballY = UIController.getInstance().getYSphere();
		ballImg.setLocation(ballX, ballY);
		this.add(ballImg);
	}

	

	public void arrangeUIComponents() throws IOException {
		UIController.getInstance().startGameLogic();
		ballImg.setLocation(UIController.getInstance().getXSphere(), UIController.getInstance().getYSphere());
		removeComponent(UIController.getInstance().getToBeDeleted()[0],UIController.getInstance().getToBeDeleted()[1]);
		removeComponent(UIController.getInstance().getToBeDeletedLaserObs()[0],UIController.getInstance().getToBeDeletedLaserObs()[1]);
		removeAbility(UIController.getInstance().getToBeDeletedAbility()[0],UIController.getInstance().getToBeDeletedAbility()[1]);
		moveLaser();
		moveObstacle();
		moveBullets();
		moveAbilities();
		this.revalidate();
		this.repaint();
		createLaser();
		addRandomHollow();
		if(!UIController.getInstance().getSphereIsUnstoppable() && control) {
			this.remove(ballImg);
			createBall(UIController.getInstance().getSphereIsUnstoppable());
			control = false;
		}
		
		if(UIController.getInstance().getGameFinished() != 0) {
			gameOver();
			timer.cancel();
		}
		if(UIController.getInstance().isPlayChanceSound()) {
			playSound("chance");
			UIController.getInstance().setPlayChanceSound(false);
		}
		
		if(UIController.getInstance().isExpansionSound()) {
			playSound("expansion");
			UIController.getInstance().setExpansionSound(false);
		}
	}

	public void updateScore(int time) {
		UIController.getInstance().setScore(UIController.getInstance().getScore() + 10);
	}


	private void gameOver() {
		resetJLabel(bulletList);
		resetJLabel(laserList);
		resetJLabel(chanceGiving);
		resetJLabel(magicalHex);
		resetJLabel(unstoppableSphere);
		resetJLabel(paddleExpansion);
		resetJLabel(hollowPurple);
		UIController.getInstance().setTime(0);
		UIController.getInstance().setChances(3);
		UIController.getInstance().resetgameLists();
		this.removeAll();
		this.revalidate();
		this.repaint();
		Window wind = SwingUtilities.getWindowAncestor(this);
		wind.dispose();
		if (UIController.getInstance().getGameFinished() == 1) new GameOverFrame(true);
		if (UIController.getInstance().getGameFinished() == 2) new GameOverFrame(false);
		UIController.getInstance().setScore(0);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		isKeyPressed = true;
		if (e.getKeyCode() == 37) {
			UIController.getInstance().setnoblePhantasmXVelocity(-L / 2);
		}
		if (e.getKeyCode() == 39) {
			UIController.getInstance().setnoblePhantasmXVelocity(L / 2);
		}
		if (e.getKeyCode() == 65) {
			UIController.getInstance().setRotationDirection(true);
			UIController.getInstance().setRotationAngle();
		}
		if (e.getKeyCode() == 68) {
			UIController.getInstance().setRotationDirection(false);
			UIController.getInstance().setRotationAngle();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		isKeyPressed = false;
		if (e.getKeyCode() == 37) {
			UIController.getInstance().setnoblePhantasmXVelocity(0);
		}

		if (e.getKeyCode() == 39) {
			UIController.getInstance().setnoblePhantasmXVelocity(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	public void placeObstacles(String type) throws IOException {
		Image img = null;
		ImageIcon icon;
		JLabel iconLabel;
		img = ImageIO.read(new File("src/images/gift_obstacle.png")).getScaledInstance(L / 5, 20,
				java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		iconLabel = new JLabel(icon);
		for (int i = 0; i < UIController.getInstance().getListSize(type); i++) {
			switch (type) {
			case "WallMaria":
				img = ImageIO.read(new File("src/images/simple_obstacle.png")).getScaledInstance(L / 5, 20,
						java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img);
				iconLabel = new JLabel(icon);
				simpleList.add(iconLabel);
				break;
			case "SteinsGate":
				int chance = UIController.getInstance().getItemLive(i);
				img = ImageIO.read(new File("src/images/firm_obstacle" + chance + ".png")).getScaledInstance(L / 5, 20,
						java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img);
				iconLabel = new JLabel(icon);
				firmList.add(iconLabel);
				break;
			case "PandorasBox":
				img = ImageIO.read(new File("src/images/explosive_obstacle.png")).getScaledInstance(L / 5, 20,
						java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img);
				iconLabel = new JLabel(icon);
				explosiveList.add(iconLabel);
				break;
			case "GiftBox":
				img = ImageIO.read(new File("src/images/gift_obstacle.png")).getScaledInstance(L / 5, 20,
						java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img);
				iconLabel = new JLabel(icon);
				giftList.add(iconLabel);
				break;
			case "hollowPurple":
				img = ImageIO.read(new File("src/images/hollow_purple.png")).getScaledInstance(L / 5, 20,
						java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img);
				iconLabel = new JLabel(icon);
				break;
			default:

			}

			int x = (int) UIController.getInstance().getObstaclexLoc(type, i);
			int y = (int) UIController.getInstance().getObstacleyLoc(type, i);

			iconLabel.setLocation(x, y);
			iconLabel.setSize(L / 5, 20);
			this.add(iconLabel);
		}

	}
	

	private void initializeBulletObs(int num, int xLoc, int yLoc) throws IOException {
		Image bulletImg = ImageIO.read(new File("src/images/bomb.png"));
		ImageIcon bulletIcon = new ImageIcon(bulletImg);
		final JLabel bulletIconLabel = new JLabel(bulletIcon);
		bulletList.add(bulletIconLabel);
		int x = xLoc;
		int y = yLoc;
		bulletIconLabel.setLocation(x, y);
		bulletIconLabel.setSize(L / 5, 20);
		this.add(bulletIconLabel);
		playSound("bullet");
	}

	public void moveBullets() {
		removeBullet(UIController.getInstance().getToBeDeletedBullet()[0],UIController.getInstance().getToBeDeletedBullet()[1]);
		for (int t = 0; t < bulletList.size(); t++) {
			int xLoc = (int) UIController.getInstance().getObstaclexLoc("Bullet",t);
			int yLoc = (int) UIController.getInstance().getObstacleyLoc("Bullet",t);
			bulletList.get(t).setLocation(xLoc, yLoc);
		}
	}


	public void moveLaser() {
		removeLaser(UIController.getInstance().getToBeDeletedLaser()[0],UIController.getInstance().getToBeDeletedLaser()[1]);
		for (int t = 0; t < laserList.size(); t++) {
			int xLoc = (int) UIController.getInstance().getLaserX(t);
			int yLoc = (int) UIController.getInstance().getLaserY(t);
			laserList.get(t).setLocation(xLoc, yLoc);
		}
	}

	public void moveObstacle() {
		for (int t = 0; t < firmList.size(); t++) {
			int xLoc = UIController.getInstance().getObstaclexLoc("SteinsGate",t);
			int yLoc = UIController.getInstance().getObstacleyLoc("SteinsGate",t);
			firmList.get(t).setLocation(xLoc, yLoc);
		}
		for (int t = 0; t < simpleList.size(); t++) {
			int xLoc = UIController.getInstance().getObstaclexLoc("WallMaria",t);
			int yLoc = UIController.getInstance().getObstacleyLoc("WallMaria",t);
			simpleList.get(t).setLocation(xLoc, yLoc);
		}
		for (int t = 0; t < explosiveList.size(); t++) {
			int xLoc = (int) UIController.getInstance().getObstaclexLoc("PandorasBox",t);
			int yLoc = (int) UIController.getInstance().getObstacleyLoc("PandorasBox",t);
			explosiveList.get(t).setLocation(xLoc, yLoc);
		}

	}

	public void moveAbilities() {
		for (int t = 0; t < chanceGiving.size(); t++) {
			int xLoc = UIController.getInstance().getAbilityxLoc("ChanceGiving",t);
			int yLoc = UIController.getInstance().getAbilityyLoc("ChanceGiving",t);
			chanceGiving.get(t).setLocation(xLoc, yLoc);
		}
		for (int t = 0; t < magicalHex.size(); t++) {
			int xLoc = UIController.getInstance().getAbilityxLoc("MagicalHex",t);
			int yLoc = UIController.getInstance().getAbilityyLoc("MagicalHex",t);
			magicalHex.get(t).setLocation(xLoc, yLoc);
		}
		for (int t = 0; t < unstoppableSphere.size(); t++) {
			int xLoc = UIController.getInstance().getAbilityxLoc("UnstoppableSphere",t);
			int yLoc = UIController.getInstance().getAbilityyLoc("UnstoppableSphere",t);
			unstoppableSphere.get(t).setLocation(xLoc, yLoc);
		}
		for (int t = 0; t < paddleExpansion.size(); t++) {
			int xLoc = UIController.getInstance().getAbilityxLoc("PaddleExpansion",t);
			int yLoc = UIController.getInstance().getAbilityyLoc("PaddleExpansion",t);
			paddleExpansion.get(t).setLocation(xLoc, yLoc);
		}
	}

	private void initializeAbilities(String type, int num, int xLoc, int yLoc) throws IOException {
		Image img = null;
		ImageIcon icon;
		JLabel iconLabel = null;

		switch (type) {
		case "ChanceGiving":
			img = ImageIO.read(new File("src/images/heart_bubble.png"));
			icon = new ImageIcon(img);
			iconLabel = new JLabel(icon);
			chanceGiving.add(iconLabel);
			break;
		case "MagicalHex":
			img = ImageIO.read(new File("src/images/firegun_bubble.png"));
			icon = new ImageIcon(img);
			iconLabel = new JLabel(icon);
			magicalHex.add(iconLabel);

			break;
		case "UnstoppableSphere":
			img = ImageIO.read(new File("src/images/unstoppable_bubble.png"));
			icon = new ImageIcon(img);
			iconLabel = new JLabel(icon);
			unstoppableSphere.add(iconLabel);
			break;
		case "PaddleExpansion":
			img = ImageIO.read(new File("src/images/expansion_bubble.png"));
			icon = new ImageIcon(img);
			iconLabel = new JLabel(icon);
			paddleExpansion.add(iconLabel);
			break;
		default:
		}
		int x = xLoc;
		int y = yLoc;
		iconLabel.setLocation(x, y);
		iconLabel.setSize(20, 20);
		this.add(iconLabel);

	}

	public void stopTheGame() {
		UIController.getInstance().setSphereXVelocity(0);
		UIController.getInstance().setSphereYVelocity(0);
		UIController.getInstance().setnoblePhantasmXVelocity(0);
	}

	public boolean intersects(Component component, Component comp) {
		Area areaA = new Area(component.getBounds());
		Area areaB = new Area(comp.getBounds());
		return areaA.intersects(areaB.getBounds2D());
	}

	public boolean intersectsOf(Component component, Polygon polyPaddle2) {
		Area areaA = new Area(component.getBounds());
		Area areaB = new Area(polyPaddle2.getBounds());
		return areaA.intersects(areaB.getBounds2D());

	}


	public void addRandomHollow() throws IOException {
		ArrayList<int[]> list = UIController.getInstance().getHollowLocs();
		for (int i = 0; i < list.size(); i++) {
			Image img = null;
			ImageIcon icon;
			JLabel iconLabel;
			img = ImageIO.read(new File("src/images/hollow_purple.png")).getScaledInstance(L / 5, 20,
					java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(img);
			iconLabel = new JLabel(icon);
			iconLabel.setLocation(list.get(i)[0], list.get(i)[1]);
			iconLabel.setSize(L / 5, 20);
			hollowPurple.add(iconLabel);
			this.add(iconLabel);
		}
		UIController.getInstance().setHollowLocs(new ArrayList<int[]>());
	}

	public void resetJLabel(ArrayList<JLabel> list) {
		for (JLabel Img : list) {
			this.remove(Img);
		}
		list.removeAll(list);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g2d = (Graphics2D) g;
		g2d.setColor(Color.orange);
		int centerX = UIController.getInstance().getXnoblePhantasm() + UIController.getInstance().getNoblePhantasmWidth() / 2;
		int centerY = UIController.getInstance().getYnoblePhantasm() + UIController.getInstance().getNoblePhantasmHeight() / 2;
		double theta = UIController.getInstance().getRotationAngle() * Math.PI / 180;
		int x1 = UIController.getInstance().getXnoblePhantasm();
		int x2 = x1 + UIController.getInstance().getNoblePhantasmWidth();
		int x3 = x2;
		int x4 = x1;
		int y1 = UIController.getInstance().getYnoblePhantasm();
		int y2 = y1;
		int y3 = y1 + UIController.getInstance().getNoblePhantasmHeight();
		int y4 = y3;

		int rotatedx1 = (int) rotationCalculatorX(x1, -y1, centerX, -centerY, theta);
		int rotatedx2 = (int) rotationCalculatorX(x2, -y2, centerX, -centerY, theta);
		int rotatedx3 = (int) rotationCalculatorX(x3, -y3, centerX, -centerY, theta);
		int rotatedx4 = (int) rotationCalculatorX(x4, -y4, centerX, -centerY, theta);
		int rotatedy1 = (int) -rotationCalculatorY(x1, -y1, centerX, -centerY, theta);
		int rotatedy2 = (int) -rotationCalculatorY(x2, -y2, centerX, -centerY, theta);
		int rotatedy3 = (int) -rotationCalculatorY(x3, -y3, centerX, -centerY, theta);
		int rotatedy4 = (int) -rotationCalculatorY(x4, -y4, centerX, -centerY, theta);

		int[] xLocs = { rotatedx1, rotatedx2, rotatedx3, rotatedx4 };
		int[] yLocs = { rotatedy1, rotatedy2, rotatedy3, rotatedy4 };
		UIController.getInstance().setxLocations(xLocs);
		UIController.getInstance().setyLocations(yLocs);
		polyPaddle = new Polygon(xLocs, yLocs, 4);
		g2d.draw(polyPaddle);
		g2d.fill(polyPaddle);

	}

	public double rotationCalculatorX(int xLoc, int yLoc, int centerX, int centerY, double d) {

		double result = (Math.cos(d) * (xLoc - centerX) + Math.sin(d) * (yLoc - centerY) + centerX);
		return result;
	}

	public double rotationCalculatorY(int xLoc, int yLoc, int centerX, int centerY, double d) {
		double result = (Math.cos(d) * (yLoc - centerY) - Math.sin(d) * (xLoc - centerX) + centerY);
		return result;
	}

	public JLabel getBallImg() {
		return ballImg;
	}

	public void setBallImg(JLabel ballImg) {
		this.ballImg = ballImg;
	}

	public String getImgPath_enchantedSphere() {
		return imgPath_enchantedSphere;
	}

	public void setImgPath_enchantedSphere(String imgPath_enchantedSphere) {
		this.imgPath_enchantedSphere = imgPath_enchantedSphere;
	}

	public void removeComponent(int x,int y) {
		if( x != -1 && y!= -1) {
			removeComponentHelper(x, y, simpleList);
			removeFirmHelper(x, y, firmList);
			removeComponentHelper(x, y, explosiveList);
			removeComponentHelper(x, y, giftList);
			removeComponentHelper(x, y, hollowPurple);
		}
		
	}
	
	public void removeComponentHelper(int x,int y,ArrayList<JLabel> list) {
		int index = -1;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getX()==x && list.get(i).getY()==y) {
				this.remove(list.get(i));
				index = i;
			}
		}
		if(index != -1) {
			list.remove(index);
			if(list == explosiveList) {
				try {
					initializeBulletObs(bulletCounter, x, y);
				} catch (IOException e) {
					e.printStackTrace();
				}
				bulletCounter++;
			}
			if(list == giftList) {
				try {
					playSound("magic");
					initializeAbilities(UIController.getInstance().getAbilityType(), bulletCounter, x, y);
				} catch (IOException e) {
					e.printStackTrace();
				}
				bulletCounter++;
			}
			
			if(list == simpleList) {
				playSound("simple");
			}
		}
	}
	
	public void removeFirmHelper(int x,int y, ArrayList<JLabel> list) {
		int indexRemoved = -1;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getX()==x && list.get(i).getY()==y) {
				indexRemoved = i;
			}
		}
		
		if(indexRemoved != -1) {
			int lives = UIController.getInstance().getLiveOfObstacle();
			if (lives == 0 || UIController.getInstance().getSphereIsUnstoppable()) {
				playSound("firm");
				this.remove(list.get(indexRemoved));
				list.remove(indexRemoved);
			}else {
				Image FirmObsImg = null;
				try {
					FirmObsImg = ImageIO.read(new File("src/images/firm_obstacle" + lives + ".png"))
							.getScaledInstance(L / 5, 20, java.awt.Image.SCALE_SMOOTH);
				} catch (IOException e) {
					e.printStackTrace();
				}
				ImageIcon firmIcon = new ImageIcon(FirmObsImg);
				final JLabel firmIconLabel = new JLabel(firmIcon);
				playSound("firm");
				this.remove(list.get(indexRemoved));
				list.remove(indexRemoved);
				list.add(indexRemoved, firmIconLabel);
				firmIconLabel.setLocation(x, y);
				firmIconLabel.setSize(L / 5, 20);
				this.add(firmIconLabel);
			}
			
		}
	}
	
	private void removeAbility(int x,int y) {
		if( x != -1 && y!= -1) {
			removeAbilityHelper(x, y, chanceGiving);
			removeAbilityHelper(x, y, magicalHex);
			removeAbilityHelper(x, y, unstoppableSphere);
			removeAbilityHelper(x, y, paddleExpansion);
		}
		
	}
	
	private void removeAbilityHelper(int x, int y, ArrayList<JLabel> list) {
		int index = -1;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getX()==x && list.get(i).getY()==y) {
				this.remove(list.get(i));
				index = i;
			}
		}
		
		if(index != -1) {
			list.remove(index);
			
			if(list == unstoppableSphere) {
				control = true;
				this.remove(ballImg);
				createBall(UIController.getInstance().getSphereIsUnstoppable());
			}
		}
		
		
	}
	
	private void createLaser() {
		if(UIController.getInstance().isPutLaser()) {
			Image laserImg = null;
			try {
				laserImg = ImageIO.read(new File("src/images/laser.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			ImageIcon laserIcon = new ImageIcon(laserImg);
			final JLabel laserIconLabel = new JLabel(laserIcon);
			laserList.add(laserIconLabel);
			int x = (int) UIController.getInstance().getLaserX(laserList.size()-1);
			int y = (int) UIController.getInstance().getLaserY(laserList.size()-1);
			laserIconLabel.setLocation(x, y);
			laserIconLabel.setSize(6, 26);
			this.add(laserIconLabel);
			playSound("laser");
			UIController.getInstance().setPutLaser(false);
		}
	}
	
	private void removeLaser(int x,int y) {
		if( x != -1 && y!= -1) {
			removeLaserHelper(x, y, laserList);
		}
		
	}
	
	private void removeLaserHelper(int x, int y, ArrayList<JLabel> list) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getX()==x && list.get(i).getY()==y) {
				this.remove(list.get(i));
				list.remove(i);
				break;
			}
		}
		
	}
	
	public void removeBullet(int x, int y) {
		if( x != -1 && y!= -1) {
			removeBulletHelper(x, y, bulletList);
		}
	}
	
	public void removeBulletHelper(int x,int y,ArrayList<JLabel> list) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getX()==x && list.get(i).getY()==y) {
				this.remove(list.get(i));
				list.remove(i);
				break;			
			}
		}
	}
	
	public static synchronized void playSound(String type) {
		  new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
		        File file = new File(path + "/src/sounds/"+type +".wav");
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
	}
	

}