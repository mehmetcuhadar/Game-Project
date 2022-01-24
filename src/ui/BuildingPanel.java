package ui;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BuildingPanel extends JPanel {

	private int width;
	private int height;
	private int L;
	int mouseOldX;
	int mouseOldY;
	int oldx=0;
	int oldy=0;
	int newX = 0;
	int newY = 0;
	int placex=0;
	int placey=0;
	ArrayList<int[]> availablePlaces = UIController.getInstance().getAvailablePlaces();

	public BuildingPanel(int numOfSimObs, int numOfFirmObs, int numOfExpObs, int numOfGiftObs, int width, int height,
			JFrame frame) throws IOException {
		this.width = width;
		this.height = height;
		this.setLayout(null);
		initializeSimpleObs(numOfSimObs);
		initializeFirmObs(numOfFirmObs);
		initializeExpObs(numOfExpObs);
		initializeGiftObs(numOfGiftObs);

	}

	private void initializeSimpleObs(int num) throws IOException {
		L = (this.width * 10) / 100;
		Random rand = new Random();
		for (int i = 0; i < num; i++) {
			Image simpleObsImg = ImageIO.read(new File("src/images/simple_obstacle.png")).getScaledInstance(L / 5, 20,
					java.awt.Image.SCALE_SMOOTH);
			ImageIcon simpleIcon = new ImageIcon(simpleObsImg);
			final JLabel simpleIconLabel = new JLabel(simpleIcon);
			int index = rand.nextInt(availablePlaces.size());
			UIController.getInstance().addObstacleTolist("WallMaria",L / 5, 20);
			UIController.setObstacleID(i, "WallMaria");
			int x = availablePlaces.get(index)[0];
			int y = availablePlaces.get(index)[1];
			simpleIconLabel.setLocation(x, y);
			simpleIconLabel.setSize(L / 5, 20);
			availablePlaces.remove(index);
			UIController.getInstance().setLocationToObstacle("WallMaria", x, y);
			this.add(simpleIconLabel);
			simpleIconLabel.addMouseMotionListener(new MouseMotionAdapter() {

				@Override
				public void mouseDragged(MouseEvent e) {
					Object draggedObj = e.getSource();
					oldx = ((Component) draggedObj).getX();
					oldy = ((Component) draggedObj).getY();
					newX = oldx + e.getX();
					newY = oldy + e.getY();
					
					simpleIconLabel.setLocation(newX, newY);
					int updatedID = UIController.getInstance().getObstacleID("WallMaria", oldx, oldy);
					UIController.getInstance().updateLocation("WallMaria", updatedID, newX, newY);
					if (check(simpleIconLabel) == false) {
						UIController.getInstance().updateLocation("WallMaria", updatedID, oldx, oldy);
						simpleIconLabel.setLocation(oldx, oldy);
					}
					if(newY>=500 || newY<=0) {
						newY = oldy;
						UIController.getInstance().updateLocation("WallMaria", updatedID, newX, newY);
						simpleIconLabel.setLocation(newX, oldy);
					}
					if(newX>=1200 || newX<=80) {
						newX = oldx;
						UIController.getInstance().updateLocation("WallMaria", updatedID, newX, newY);
						simpleIconLabel.setLocation(oldx, newY);
					}
				}
			});

		}

	}

	private void initializeFirmObs(int num) throws IOException {
		Random rand = new Random();
		for (int i = 0; i < num; i++) {
			UIController.getInstance().addObstacleTolist("SteinsGate",L / 5, 20);
			int chance = UIController.getInstance().getItemLive(i);
			UIController.setObstacleID(i, "SteinsGate");
			
			Image FirmObsImg = ImageIO.read(new File("src/images/firm_obstacle" + chance + ".png")).getScaledInstance(L / 5, 20,
					java.awt.Image.SCALE_SMOOTH);
			ImageIcon firmIcon = new ImageIcon(FirmObsImg);
			final JLabel firmIconLabel = new JLabel(firmIcon);
			int index = rand.nextInt(availablePlaces.size());

			int x = availablePlaces.get(index)[0];
			int y = availablePlaces.get(index)[1];
			availablePlaces.remove(index);
			firmIconLabel.setLocation(x, y);
			firmIconLabel.setSize(L / 5, 20);
			UIController.getInstance().setLocationToObstacle("SteinsGate", x, y);
			this.add(firmIconLabel);
			firmIconLabel.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					Object draggedObj = e.getSource();
					int oldx = ((Component) draggedObj).getX();
					int oldy = ((Component) draggedObj).getY();
					int newX = firmIconLabel.getX() + e.getX();
					int newY = firmIconLabel.getY() + e.getY();
					firmIconLabel.setLocation(newX, newY);
					int updatedID = UIController.getInstance().getObstacleID("SteinsGate", oldx, oldy);
					UIController.getInstance().updateLocation("SteinsGate", updatedID, newX, newY);
					if (check(firmIconLabel) == false) {
						UIController.getInstance().updateLocation("SteinsGate", updatedID, oldx, oldy);
						firmIconLabel.setLocation(oldx, oldy);
					}
					if(newY>=500 || newY<=0) {
						newY = oldy;
						UIController.getInstance().updateLocation("SteinsGate", updatedID, newX, newY);
						firmIconLabel.setLocation(newX, oldy);
					}
					if(newX>=1200 || newX<=80) {
						newX = oldx;
						UIController.getInstance().updateLocation("SteinsGate", updatedID, newX, newY);
						firmIconLabel.setLocation(oldx, newY);
					}
					
				}

			});
		}
	}

	private void initializeExpObs(int num) throws IOException {
		Random rand = new Random();
		for (int i = 0; i < num; i++) {
			Image expImg = ImageIO.read(new File("src/images/explosive_obstacle.png")).getScaledInstance(15, 15,
					java.awt.Image.SCALE_SMOOTH);
			ImageIcon expIcon = new ImageIcon(expImg);
			final JLabel expIconLabel = new JLabel(expIcon);
			int index = rand.nextInt(availablePlaces.size());
			UIController.getInstance().addObstacleTolist("PandorasBox",15, 15);
			UIController.setObstacleID(i, "PandorasBox");
			int x = availablePlaces.get(index)[0];
			int y = availablePlaces.get(index)[1];
			availablePlaces.remove(index);
			expIconLabel.setLocation(x, y);
			expIconLabel.setSize(15, 15);
			UIController.getInstance().setLocationToObstacle("PandorasBox", x, y);
			this.add(expIconLabel);
			expIconLabel.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					Object draggedObj = e.getSource();
					int oldx = ((Component) draggedObj).getX();
					int oldy = ((Component) draggedObj).getY();
					int newX = expIconLabel.getX() + e.getX();
					int newY = expIconLabel.getY() + e.getY();
					expIconLabel.setLocation(newX, newY);
					int updatedID = UIController.getInstance().getObstacleID("PandorasBox", oldx, oldy);
					UIController.getInstance().updateLocation("PandorasBox", updatedID, newX, newY);
					if (check(expIconLabel) == false) {
						UIController.getInstance().updateLocation("PandorasBox", updatedID, oldx, oldy);
						expIconLabel.setLocation(oldx, oldy);
					}
					if(newY>=500 || newY<=0) {
						newY = oldy;
						UIController.getInstance().updateLocation("PandorasBox", updatedID, newX, newY);
						expIconLabel.setLocation(newX, oldy);
					}
					if(newX>=1200 || newX<=80) {
						newX = oldx;
						UIController.getInstance().updateLocation("PandorasBox", updatedID, newX, newY);
						expIconLabel.setLocation(oldx, newY);
					}
				}

			});

		}
	}

	private void initializeGiftObs(int num) throws IOException {

		Random rand = new Random();
		for (int i = 0; i < num; i++) {
			Image giftImg = ImageIO.read(new File("src/images/gift_obstacle.png")).getScaledInstance(L / 5, 20,
					java.awt.Image.SCALE_SMOOTH);
			ImageIcon giftIcon = new ImageIcon(giftImg);
			final JLabel giftIconLabel = new JLabel(giftIcon);
			int index = rand.nextInt(availablePlaces.size());
			UIController.getInstance().addObstacleTolist("GiftBox",L / 5, 20);
			UIController.setObstacleID(i, "GiftBox");
			int x = availablePlaces.get(index)[0];
			int y = availablePlaces.get(index)[1];
			availablePlaces.remove(index);
			giftIconLabel.setLocation(x, y);
			giftIconLabel.setSize(L / 5, 20);
			UIController.getInstance().setLocationToObstacle("GiftBox", x, y);
			this.add(giftIconLabel);
			giftIconLabel.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					Object draggedObj = e.getSource();
					int oldx = ((Component) draggedObj).getX();
					int oldy = ((Component) draggedObj).getY();
					int newX = giftIconLabel.getX() + e.getX();
					int newY = giftIconLabel.getY() + e.getY();
					giftIconLabel.setLocation(newX, newY);
					int updatedID = UIController.getInstance().getObstacleID("GiftBox", oldx, oldy);
					UIController.getInstance().updateLocation("GiftBox", updatedID, newX, newY);
					if (check(giftIconLabel) == false) {
						UIController.getInstance().updateLocation("GiftBox", updatedID, oldx, oldy);
						giftIconLabel.setLocation(oldx, oldy);
					}
					if(newY>=500 || newY<=0) {
						newY = oldy;
						UIController.getInstance().updateLocation("GiftBox", updatedID, newX, newY);
						giftIconLabel.setLocation(newX, oldy);
					}
					if(newX>=1200 || newX<=80) {
						newX = oldx;
						UIController.getInstance().updateLocation("GiftBox", updatedID, newX, newY);
						giftIconLabel.setLocation(oldx, newY);
					}
				}

			});

		}
	}
	
	public boolean intersects(Component component, Component comp) {
		Area areaA = new Area(component.getBounds());
		Area areaB = new Area(comp.getBounds());

		return areaA.intersects(areaB.getBounds2D());
	}

	private boolean check(Component comp) {
		for (int i = 0; i < this.getComponentCount(); i++) {
			Component component = this.getComponent(i);
			if (component != comp) {
				if (intersects(component, comp) == true) {
					return false;
				}
			}

		}
		return true;

	}

}