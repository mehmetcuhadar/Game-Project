package domain.game;

import java.awt.Component;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;


import domain.Ymir.*;
import domain.magicalAbilities.Ability;

import domain.obstacles.Obstacle;
import domain.playerObjects.Laser;
import ui.UIController;

public class Settings {
	// Overview: The game logic here Singleton
	private int currentLive = 0;
	private int hollowNumber = 0;
	private int abilityStartTime = 0;
	private int abilityFinishTime = 0;
	private static Settings instance;
	public int firmObsNumber = 0;
	public int expObsNumber = 0;
	public int simpleObsNumber = 0;
	public int giftObsNumber = 0;
	public int chanceCounter = 0;
	public int bombCounter = 0;
	public int laserIndex = -1;
	int[] toBeDeleted = {-1,-1};
	private String abilityType = "";
	int[] toBeDeletedAbility = {-1,-1};
	int[] toBeDeletedLaser = {-1,-1};;
	int[] toBeDeletedLaserObs = {-1,-1};
	int[] toBeDeletedBullet = {-1,-1};
	
	private boolean laserCondition = false;
	private int laserCounter = 0;
	private boolean hollowControl = false; 
	
	private boolean isLeft = false;
	private boolean putLaser = false;
	private boolean playChanceSound = false;
	private boolean expansionSound = false;

	// The abstraction function is 
	// AF(settings) = {settings.simpleObstacles.get(i)| 0<=i< settings.simpleObsNumber }
	// AF(settings) = {settings.firmObstacles.get(i)| 0<=i< settings.firmObsNumber }
	// AF(settings) = {settings.livesOfFirmObstacles.get(i)| 0<=i< settings.firmObsNumber }
	// AF(settings) = {settings.expObstacles.get(i)| 0<=i< settings.expObsNumber }
	// AF(settings) = {settings.giftObstacles.get(i)| 0<=i< settings.simpleObsNumber }
	//Constructors
	private Settings() {

	}
	//Instance
	public static Settings getInstance() {
		if (instance == null)
			instance = new Settings();
		return instance;
	}
	//List init
	public List<Obstacle> simpleObstacles = new ArrayList<Obstacle>();
	public List<Obstacle> firmObstacles = new ArrayList<Obstacle>();
	public List<Integer> livesOfFirmObstacles = new ArrayList<Integer>();
	public List<Obstacle> giftObstacles = new ArrayList<Obstacle>();
	public List<Obstacle> expObstacles = new ArrayList<Obstacle>();
	public List<Obstacle> bullet = new ArrayList<Obstacle>();
	public List<Obstacle> hollowPurpleObstacles = new ArrayList<Obstacle>();
	public List<Ability> chanceGiving = new ArrayList<Ability>();
	public List<Ability> magicalHex = new ArrayList<Ability>();
	public List<Ability> unstoppableSphere = new ArrayList<Ability>();
	public List<Ability> paddleExpansion = new ArrayList<Ability>();
	public List<Laser> laserList = new ArrayList<Laser>();
	public ArrayList<int[]> hollowLocs = new ArrayList<int[]>(); 
	public ArrayList<String> saveList = new ArrayList<String>();
	public ArrayList<String> loadList = new ArrayList<String>();

	ArrayList<int[]> availablePlaces = determinePlaces();

	Timer timer = new Timer();

	public int time = 0;
	public int numOfSavedGames = 0;
	public String selectedSavedGame;
	public String saveType;
	public String ymirsAbility;
	private boolean isExpanded;
	private boolean isUnstoppable;
	DoubleAccel doubleAccel = new DoubleAccel();
	InfiniteVoid infiniteVoid = new InfiniteVoid();
	private int indexRemoved;

	public boolean isExpanded() {
		return this.isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public boolean isUnstoppable() {
		return isUnstoppable;
	}

	public void setIsUnstoppable(boolean isUnstoppable) {
		this.isUnstoppable = isUnstoppable;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getNumOfSavedGames() {
		return numOfSavedGames;
	}

	public void setNumOfSavedGames(int numOfSavedGames) {
		this.numOfSavedGames = numOfSavedGames;
	}

	public String getSelectedSavedGame() {
		return selectedSavedGame;
	}

	public void setSelectedSavedGame(String selectedSavedGame) {
		this.selectedSavedGame = selectedSavedGame;
	}

	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	public List<Obstacle> getSimpleObstacles() {
		return simpleObstacles;
	}

	public void setSimpleObstacles(List<Obstacle> simpleObstacles) {
		this.simpleObstacles = simpleObstacles;
	}

	public List<Obstacle> getFirmObstacles() {
		return firmObstacles;
	}

	public void setFirmObstacles(List<Obstacle> firmObstacles) {
		this.firmObstacles = firmObstacles;
	}

	public List<Obstacle> getHollowPurpleObstacles() {
		return hollowPurpleObstacles;
	}

	public void setHollowPurpleObstacles(List<Obstacle> hollowPurpleObstacles) {
		this.hollowPurpleObstacles = hollowPurpleObstacles;
	}

	public void setExpObstacles(List<Obstacle> expObstacles) {
		this.expObstacles = expObstacles;
	}

	public List<Obstacle> getGiftObstacles() {
		return giftObstacles;
	}

	public void setGiftObstacles(List<Obstacle> giftObstacles) {
		this.giftObstacles = giftObstacles;
	}

	public int getLivesOfFirmObstacles() {
		return this.currentLive;
	}

	public void setItemOfFirmObstacles(int index) {
		this.livesOfFirmObstacles.set(index, this.livesOfFirmObstacles.get(index) - 1);
	}

	public void initializeLivesOfFirmObstacles(int id, int lives) {
		this.livesOfFirmObstacles.set(id, lives);
	}

	public ArrayList<String> getSaveList() {
		return saveList;
	}

	public void setSaveList(ArrayList<String> saveList) {
		this.saveList = saveList;
	}

	public ArrayList<String> getLoadList() {
		return loadList;
	}

	public void setLoadList(ArrayList<String> loadList) {
		this.loadList = loadList;
	}

	public ArrayList<int[]> getAvailablePlaces() {
		return availablePlaces;
	}

	public boolean isPutLaser() {
		return putLaser;
	}
	public void setPutLaser(boolean putLaser) {
		this.putLaser = putLaser;
	}

	public List<Obstacle> getLists(String type) {
		if (type == "WallMaria") {
			return simpleObstacles;
		} else if (type == "SteinsGate") {
			return firmObstacles;
		} else if (type == "PandorasBox") {
			return expObstacles;
		} else if (type == "Bullet") {
			return bullet;
		} else if (type == "hollowPurple") {
			return hollowPurpleObstacles;
		} else if(type=="GiftBox") {
			return giftObstacles;
		}
		return null;
	}

	public void addObstacles(String type) {
		Random rand = new Random();
		//Requires: String argument that defines obstacle type
		//Modifies: this
		//Effects: add obstacle to its list
		if (type.equals("WallMaria")) {
			simpleObstacles.add(ObstacleFactory.getInstance().createObstacle(type));
		} else if (type.equals("SteinsGate")) {
			firmObstacles.add(ObstacleFactory.getInstance().createObstacle(type));
			livesOfFirmObstacles.add(rand.nextInt(5) + 1);
		} else if (type.equals("PandorasBox")) {
			expObstacles.add(ObstacleFactory.getInstance().createObstacle(type));
		} else if (type.equals("Bullet")) {
			bullet.add(ObstacleFactory.getInstance().createObstacle(type));
		} else if (type.equals("hollowPurple")) {
			hollowPurpleObstacles.add(ObstacleFactory.getInstance().createObstacle(type));
		} else if(type.equals("GiftBox")){
			giftObstacles.add(ObstacleFactory.getInstance().createObstacle(type));
		}

	}

	public void setLocation(String type, int x, int y) {
		//Requires: String argument that defines obstacle type, x and y int which defines locations
		//Modifies: this
		//Effects: set the location of the obstacle in the list
		if (type.equals("WallMaria")) {
			simpleObstacles.get(simpleObstacles.size() - 1).setLocation(x, y);
		} else if (type.equals("SteinsGate")) {
			firmObstacles.get(firmObstacles.size() - 1).setLocation(x, y);
		} else if (type.equals("PandorasBox")) {
			expObstacles.get(expObstacles.size() - 1).setLocation(x, y);
		} else if (type.equals("Bullet")) {
			bullet.get(bullet.size() - 1).setLocation(x, y);
		} else if (type.equals("hollowPurple")) {
			hollowPurpleObstacles.get(hollowPurpleObstacles.size() - 1).setLocation(x, y);
		} else if(type.equals("GiftBox")) {
			giftObstacles.get(giftObstacles.size() - 1).setLocation(x, y);
		}
	}

	public void removeObs(String type, int id) {
		//Requires: String argument that defines obstacle type
		//Modifies: this
		//Effects: remove obstacle from list
		if (type.equals("WallMaria")) {
			simpleObstacles.remove(id);
		} else if (type.equals("SteinsGate")) {
			firmObstacles.remove(id);
		} else if (type.equals("PandorasBox")) {
			expObstacles.remove(id);
		} else if (type.equals("hollowPurple")) {
			hollowPurpleObstacles.remove(id);
		} else if (type.equals("Bullet")) {
			bullet.remove(id);
		} else if(type.equals("GiftBox")) {
			giftObstacles.remove(id);
		}
	}

	public void updateLocation(String type, int id, int x, int y) {
		//Requires: String argument that defines obstacle type, x and y int which defines locations
		//Modifies: this
		//Effects: update the location of the obstacle in the list
		if (type.equals("WallMaria")) {
			simpleObstacles.get(id).setLocation(x, y);
		} else if (type.equals("SteinsGate")) {
			firmObstacles.get(id).setLocation(x, y);
		} else if (type.equals("PandorasBox")) {
			expObstacles.get(id).setLocation(x, y);
		} else if (type.equals("Bullet")) {
			bullet.get(id).setLocation(x, y);
		} else if (type.equals("hollowPurple")) {
			hollowPurpleObstacles.get(id).setLocation(x, y);
		} else if(type.equals("GiftBox")) {
			giftObstacles.get(id).setLocation(x, y);
		}
	}

	public void setObstacleID(int id, String type) {
		//Requires: String argument that defines obstacle type, id which obstacle's unique id
		//Modifies: this
		//Effects: set the id of the obstacle in the list
		if (type.equals("WallMaria")) {
			simpleObstacles.get(simpleObstacles.size() - 1).setObstacleID(id);
		} else if (type.equals("SteinsGate")) {
			firmObstacles.get(firmObstacles.size() - 1).setObstacleID(id);
		} else if (type.equals("PandorasBox")) {
			expObstacles.get(expObstacles.size() - 1).setObstacleID(id);
		} else if (type.equals("Bullet")) {
			bullet.get(bullet.size() - 1).setObstacleID(id);
		} else if (type.equals("hollowPurple")) {
			hollowPurpleObstacles.get(hollowPurpleObstacles.size() - 1).setObstacleID(id);
		} else if(type.equals("GiftBox")){
			giftObstacles.get(giftObstacles.size() - 1).setObstacleID(id);
		}
	}

	public int removeBullet(int x, int y) {
		//Requires:  x and y int which defines locations
		//Modifies: this
		//Effects:  get the bullet index in the list
		for (int i = 0; i < bullet.size(); i++) {
			if (bullet.get(i) != null) {
				if (bullet.get(i).getLocation().getxLoc() == x && bullet.get(i).getLocation().getyLoc() == y) {
					return i;
				}
			}
		}
		return 0;
	}

	public int getItemLive(int index) {
		//Requires: index of the firm obstacle
		//Modifies: this
		//Effects:  get the firm obstacle live in the list
		return this.livesOfFirmObstacles.get(index);
	}

	public List<Obstacle> getBullet() {
		return bullet;
	}

	public void setBullet(List<Obstacle> bullet) {
		this.bullet = bullet;
	}

	public List<Ability> getAbilityList(String type) {
		if (type.equals("ChanceGiving")) {
			return chanceGiving;
		} else if (type.equals("MagicalHex")) {
			return magicalHex;
		} else if (type.equals("UnstoppableSphere")) {
			return unstoppableSphere;
		} else {
			return paddleExpansion;
		}
	}

	

	

	public int getRemoveAbility(String type, int x, int y) {
		if (type.equals("ChanceGiving")) {
			for (int i = 0; i < chanceGiving.size(); i++) {
				if (chanceGiving.get(i) != null) {
					if (chanceGiving.get(i).getLocation().getxLoc() == x
							&& chanceGiving.get(i).getLocation().getyLoc() == y) {
						return i;

					}
				}
			}
		} else if (type.equals("MagicalHex")) {
			for (int i = 0; i < magicalHex.size(); i++) {
				if (magicalHex.get(i) != null) {
					if (magicalHex.get(i).getLocation().getxLoc() == x
							&& magicalHex.get(i).getLocation().getyLoc() == y) {
						return i;
					}
				}
			}
		} else if (type.equals("UnstoppableSphere")) {
			for (int i = 0; i < unstoppableSphere.size(); i++) {
				if (unstoppableSphere.get(i) != null) {
					if (unstoppableSphere.get(i).getLocation().getxLoc() == x
							&& unstoppableSphere.get(i).getLocation().getyLoc() == y) {
						return i;
					}
				}
			}
		} else { // paddle expansion
			for (int i = 0; i < paddleExpansion.size(); i++) {
				if (paddleExpansion.get(i) != null) {
					if (paddleExpansion.get(i).getLocation().getxLoc() == x
							&& paddleExpansion.get(i).getLocation().getyLoc() == y) {
						return i;
					}
				}
			}

		}
		return -1;
	}

	public List<Laser> getLaserList() {
		return this.laserList;
	}

	public void removeLaser(int x) {
		laserList.remove(x);
	}

	public void addLaserToList(boolean isLeft) {
		laserList.add(new Laser(isLeft));
	}


	public double getLaserX(int index) {
		return laserList.get(index).getLocation().getxLoc();
	}

	public double getLaserY(int index) {
		return laserList.get(index).getLocation().getyLoc();
	}

	public void removeBulletItem(int x) {
		this.bullet.remove(x);
	}

	public void removeChance(int x) {
		 
		this.chanceGiving.remove(x);
	}

	public void removeUnsSphere(int x) {
		 
		this.unstoppableSphere.remove(x);
	}

	public void removePaddleExp(int x) {
		 
		this.paddleExpansion.remove(x);
	}

	public void removeMagical(int x) {
		 
		this.magicalHex.remove(x);
	}

	public void resetObsList(List<Obstacle> obsList) {
		obsList.removeAll(obsList);
	}

	public void resetAbilityList(List<Ability> abilityList) {
		abilityList.removeAll(abilityList);
	}

	public void resetLaserList(List<Laser> laserList) {
		laserList.removeAll(laserList);
	}

	public void resetlivesList(List<Integer> livesOfFirmObstacles) {
		livesOfFirmObstacles.removeAll(livesOfFirmObstacles);
	}

	public void resetsaveLoadList(List<String> saveLoadList) {
		saveLoadList.removeAll(saveLoadList);
	}

	public void resetgameLists() {
		resetObsList(simpleObstacles);
		resetObsList(firmObstacles);
		resetObsList(giftObstacles);
		resetObsList(expObstacles);
		resetObsList(bullet);
		resetlivesList(livesOfFirmObstacles);
		resetAbilityList(chanceGiving);
		resetAbilityList(magicalHex);
		resetAbilityList(unstoppableSphere);
		resetAbilityList(paddleExpansion);
		resetLaserList(laserList);

	}

	public void resetSaveLoadLists() {
		resetsaveLoadList(saveList);
		resetsaveLoadList(loadList);

	}

	public void setDoubleAccel(boolean bool) {
		doubleAccel.setActivated(bool);
	}

	public boolean getDoubleAccel() {
		return doubleAccel.isActivated();
	}

	private ArrayList<int[]> determinePlaces() {
		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int i = 80; i < 1200; i += 80) {
			for (int j = 35; j < 500; j += 50) {
				int[] arr = { i, j };
				list.add(arr);
			}
		}
		return list;
	}

	public boolean isFirmStiff(int index) {		 
		return firmObstacles.get(index).isStiff();
	}

	public boolean isSimpleStiff(int index) {
		return simpleObstacles.get(index).isStiff();
	}

	public void moveObs() {
		for (Obstacle x : firmObstacles) {
			if (!x.isStiff()) {
				if (x.isRight()) {
					x.setLocation(x.getLocation().getxLoc() + 1, x.getLocation().getyLoc());
				} else {
					x.setLocation(x.getLocation().getxLoc() - 1, x.getLocation().getyLoc());
				}
				x.setxSpeed();
			}
		}

		for (Obstacle x : simpleObstacles) {
			if (!x.isStiff()) {
				if (x.isRight()) {
					x.setLocation(x.getLocation().getxLoc() + 1, x.getLocation().getyLoc());
				} else {
					x.setLocation(x.getLocation().getxLoc() - 1, x.getLocation().getyLoc());
				}
				x.setxSpeed();
			}

		}
		double angle;
		for (Obstacle x : expObstacles) {
			angle = x.getAngle() + 0.1;
			x.setAngle(angle);
			if (angle > (2 * Math.PI)) {
				angle = 0.0;
				x.setAngle(angle);
			}
			int xMov = (int) (Math.cos(angle) * 2);
			int yMov = (int) (Math.sin(angle) * 2);
			x.setLocation(x.getLocation().getxLoc() + xMov, x.getLocation().getyLoc() + yMov);

		}
	}

	

	public void startInfiniteVoid() {
		Random rand = new Random();
		int simpleSize = this.simpleObstacles.size();
		int firmSize = this.firmObstacles.size();
		int expSize = this.expObstacles.size();
		int giftSize = this.giftObstacles.size();
		int totalObs = simpleSize + firmSize + expSize + giftSize;
		if (totalObs <= 8) {
			infiniteVoid.setFrozeEverything(true);
		} else {
			for (int i = 0; i < 8; i++) {
				int randInt = rand.nextInt(4);
				switch (randInt) {
				case 0:
					if(simpleSize != 0) {
						int randomSimple = rand.nextInt(simpleSize);
						this.simpleObstacles.get(randomSimple).setInfiniteVoid(true);
					}
					break;
				case 1:
					if(firmSize!=0) {
						int randomFirm = rand.nextInt(firmSize);
						this.firmObstacles.get(randomFirm).setInfiniteVoid(true);
					}
					break;
				case 2:
					if(expSize!=0) {
						int randomExp = rand.nextInt(expSize);
						this.expObstacles.get(randomExp).setInfiniteVoid(true);
					}
					break;
				case 3:
					if(giftSize!=0) {
						int randomGift = rand.nextInt(giftSize);
						this.giftObstacles.get(randomGift).setInfiniteVoid(true);
					}
					break;
				}
			}
		}

	}

	public void finishInfiniteVoid() {
		if (infiniteVoid.isFrozeEverything() == true) {
			infiniteVoid.setFrozeEverything(false);
		} else {
			for (int i = 0; i < this.simpleObstacles.size(); i++) {
				this.simpleObstacles.get(i).setInfiniteVoid(false);
			}
			for (int i = 0; i < this.firmObstacles.size(); i++) {
				this.firmObstacles.get(i).setInfiniteVoid(false);
			}
			for (int i = 0; i < this.expObstacles.size(); i++) {
				this.expObstacles.get(i).setInfiniteVoid(false);
			}
			for (int i = 0; i < this.giftObstacles.size(); i++) {
				this.giftObstacles.get(i).setInfiniteVoid(false);
			}
		}
	}
	
	public boolean repOk(List<Obstacle> list) {
		if (list.size() == 0) return false;
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getObstacleID()==list.get(j).getObstacleID()) return false;
			}
		}
		return true;
	}


	public void startGameLogic() {
		checkWin();
		checkPaddleCollision();
		checkReflection();
		checkBallCollision();
		checkLaserObstacle();
		checkObstacle();
		checkBullet();
		checkAbility();
		try {
			checkYmir();
		} catch (IOException e) {
			e.printStackTrace();
		}
		NeedForSpearGame.getInstance().moveSphere();
		NeedForSpearGame.getInstance().movenoblePhantasm();
		moveObs();
		moveBullet();
		moveAbilities();
		moveLaser();
		

		if (laserCondition) {
			if (laserCounter % 2 == 0) {
				createLaser();
			} else {
				createLaser();
			}
			putLaser = true;

			isLeft = !isLeft;
			laserCounter++;
			if (laserCounter == 30) {
				laserCondition = false;
				laserCounter = 0;
			}
		}
	}
	
	private void checkWin() {
		if(simpleObstacles.size() == 0 && firmObstacles.size() == 0 && expObstacles.size() == 0 && giftObstacles.size() == 0 && hollowPurpleObstacles.size() ==0) {
			NeedForSpearGame.getInstance().setGameFinished(1); //win
		}
		
	}
	private void checkYmir() throws IOException {
		if(NeedForSpearGame.getInstance().getTime() % 600 == 0) {
			ymirsAbility = NeedForSpearGame.getInstance().ymirRandom();
			if (ymirsAbility != null) {
				switch (ymirsAbility) {
				case "hollowPurple":
					addRandomHollow();
					break;
				case "doubleAccel":
					abilityStartTime = NeedForSpearGame.getInstance().getTime();
					abilityFinishTime = abilityStartTime + 15 * 20;
					NeedForSpearGame.getInstance().setDoubleAccel(true);
					NeedForSpearGame.getInstance()
							.setSphereXVelocity(NeedForSpearGame.getInstance().getSphereXVelocity() / 2);
					NeedForSpearGame.getInstance()
							.setSphereYVelocity(NeedForSpearGame.getInstance().getSphereYVelocity() / 2);
					break;
				case "infiniteVoid":
					abilityFinishTime = abilityStartTime + 15 * 20;
					abilityFinishTime = abilityStartTime + 15;
					NeedForSpearGame.getInstance().startInfiniteVoid();
				default:
					break;
				}
			}
		}
		
		if (NeedForSpearGame.getInstance().getTime() == abilityFinishTime) {
			if(ymirsAbility != null) {
				if (ymirsAbility.equals("doubleAccel")) {
					NeedForSpearGame.getInstance().setDoubleAccel(false);
					NeedForSpearGame.getInstance()
							.setSphereXVelocity(NeedForSpearGame.getInstance().getSphereXVelocity() * 2);
					NeedForSpearGame.getInstance()
							.setSphereYVelocity(NeedForSpearGame.getInstance().getSphereYVelocity() * 2);
				} else if (ymirsAbility.equals("infiniteVoid")) {
					NeedForSpearGame.getInstance().finishInfiniteVoid();

				}
			}
			
		}
		
		
	}
	private void addRandomHollow() {
		Random rand = new Random();
		for (int i = 0; i <= 7; i++) {
			int index = rand.nextInt(availablePlaces.size());
			int x = availablePlaces.get(index)[0];
			int y = availablePlaces.get(index)[1];
			while( x == -1) {
				index = rand.nextInt(availablePlaces.size());
				x = availablePlaces.get(index)[0];
				y = availablePlaces.get(index)[1];
			}
			if(availablePlaces.get(index)!=null) {
				int[] arr = {x,y};
				hollowLocs.add(arr);
				availablePlaces.remove(index);
				addObstacles("hollowPurple");
				setLocation("hollowPurple", x,y);
			}
			
		}
		
	}
	private void checkBullet(){
		toBeDeletedBullet[0] = -1;
		toBeDeletedBullet[1] = -1;
		Component bullet = new Component() {
		}; 
		int indexRemoved = -1;
		for (Obstacle b:this.bullet) {
			bullet.setBounds((int)b.getLocation().getxLoc(),(int)b.getLocation().getyLoc(), b.getWidth(), b.getHeight());
			indexRemoved = checkBulletHelper(bullet);
			if(indexRemoved != -1) break;
		}
		if(indexRemoved != -1 ) {
			this.bullet.remove(indexRemoved);
		}
	}
	
	private int checkBulletHelper(Component bullet) {
		int size = NeedForSpearGame.getInstance().getxLocations().length;
		int obstacleNumber = -1; 
		Polygon polygon = new Polygon(NeedForSpearGame.getInstance().getxLocations(), NeedForSpearGame.getInstance().getyLocations(), size);
		if (polygon.intersects(bullet.getBounds())) {
			Component removedObs = bullet;
			int x = removedObs.getX();
			int y = removedObs.getY();
			toBeDeletedBullet[0] = x;
			toBeDeletedBullet[1] = y;
			obstacleNumber = removeBullet(x, y);
			NeedForSpearGame.getInstance().decrementChances();
			if (NeedForSpearGame.getInstance().getNoblePhantasmIsExpanded()) {
				NeedForSpearGame.getInstance()
						.setNoblePhantasmWidth(NeedForSpearGame.getInstance().getNoblePhantasmWidth() / 2);
				NeedForSpearGame.getInstance().setNoblePhantasmIsExpanded(false);
			}
			if (NeedForSpearGame.getInstance().getSphereIsUnstoppable()) {
				NeedForSpearGame.getInstance().setSphereWidth(16);
				NeedForSpearGame.getInstance().setSphereHeight(16);
				NeedForSpearGame.getInstance().setSphereIsUnstoppable(false);
			}

			if (NeedForSpearGame.getInstance().getChances() == 0) {
				NeedForSpearGame.getInstance().setGameFinished(2);
				gameOver();
			}
		}
		
		return obstacleNumber;

		
	}
	
	private void checkAbility() {
		toBeDeletedAbility[0] = -1;
		toBeDeletedAbility[1] = -1;
		iterateAbilityList();
	}
	
	private void iterateAbilityList() {
		Component ability = new Component() {
		};
		boolean bool = false;
		for(int c=0;c<1;c++) {
			bool = iterateAbilityListHelper(ability, chanceGiving);
			if(bool) break;
			bool = iterateAbilityListHelper(ability, magicalHex);
			if(bool) break;
			bool = iterateAbilityListHelper(ability, unstoppableSphere);
			if(bool) break;
			bool = iterateAbilityListHelper(ability, paddleExpansion);
			if(bool) break;
		}
	}
	
	private boolean iterateAbilityListHelper(Component ability,List<Ability> list) {
		boolean bool = false;
		int index = -1;
		for(Ability a: list) {
			ability.setBounds((int)a.getLocation().getxLoc(), (int)a.getLocation().getyLoc(), a.getWidth(), a.getHeight());
			index = checkAbilityHelper(ability, list);
			if(index != -1) break;
		}
		if(index!=-1) {
			list.remove(index);
			bool = true;
		}
		return bool;
	}
	
	private int checkAbilityHelper(Component ability, List<Ability> list) {
		int size = NeedForSpearGame.getInstance().getxLocations().length;
		int abilityNumber = -1; 
		Polygon polygon = new Polygon(NeedForSpearGame.getInstance().getxLocations(), NeedForSpearGame.getInstance().getyLocations(), size);
		if (polygon.intersects(ability.getBounds())) {
			Component removedObs = ability;
			int x = removedObs.getX();
			int y = removedObs.getY();
			toBeDeletedAbility[0] = x;
			toBeDeletedAbility[1] = y;
			
			if (list == chanceGiving) {
				abilityNumber = NeedForSpearGame.getInstance().getRemoveAbility("ChanceGiving", x, y);
				playChanceSound = true;
				NeedForSpearGame.getInstance().incrementChances();
			}
			else if (list == magicalHex) {
				abilityNumber = NeedForSpearGame.getInstance().getRemoveAbility("MagicalHex", x, y);
				laserCondition = true;
				laserCounter = 0;
			}
			else if (list == unstoppableSphere) {
				abilityNumber = NeedForSpearGame.getInstance().getRemoveAbility("UnstoppableSphere", x, y);
				if (!NeedForSpearGame.getInstance().getSphereIsUnstoppable()) {
					NeedForSpearGame.getInstance().setSphereWidth(32);
					NeedForSpearGame.getInstance().setSphereHeight(32);
					NeedForSpearGame.getInstance().setSphereIsUnstoppable(true);
				}
			}
			else{ //expansion check
				abilityNumber = NeedForSpearGame.getInstance().getRemoveAbility("PaddleExpansion", x, y);
				if (!NeedForSpearGame.getInstance().getNoblePhantasmIsExpanded()) {
					NeedForSpearGame.getInstance()
							.setNoblePhantasmWidth(NeedForSpearGame.getInstance().getNoblePhantasmWidth() * 2);
					NeedForSpearGame.getInstance().setNoblePhantasmIsExpanded(true);
					expansionSound = true;
			
				}

			}
		}
		return abilityNumber;
		
	}
	
	
	
	
	public boolean checkBallCollision() {

		if (NeedForSpearGame.getInstance().getXSphere() < 0) {
			NeedForSpearGame.getInstance().setSphereX(1);
			NeedForSpearGame.getInstance().setSphereXVelocity(-NeedForSpearGame.getInstance().getSphereXVelocity());
		}

		if (NeedForSpearGame.getInstance().getXSphere() > 1250 - NeedForSpearGame.getInstance().getSphereWidth()) {
			NeedForSpearGame.getInstance().setSphereX(1250 - NeedForSpearGame.getInstance().getSphereWidth() - 1);
			NeedForSpearGame.getInstance().setSphereXVelocity(-NeedForSpearGame.getInstance().getSphereXVelocity());
		}

		if (NeedForSpearGame.getInstance().getYSphere() < 0) {
			NeedForSpearGame.getInstance().setSphereY(1);
			NeedForSpearGame.getInstance().setSphereYVelocity(-NeedForSpearGame.getInstance().getSphereYVelocity());
		}

		if (NeedForSpearGame.getInstance().getYSphere() > 700 - NeedForSpearGame.getInstance().getSphereHeight()) {
			NeedForSpearGame.getInstance().setSphereY(700 - NeedForSpearGame.getInstance().getSphereHeight() - 1);
			NeedForSpearGame.getInstance().setSphereYVelocity(-NeedForSpearGame.getInstance().getSphereYVelocity());
			if (NeedForSpearGame.getInstance().getChances() == 1) {
				UIController.getInstance().setChances(0);
				NeedForSpearGame.getInstance().setGameFinished(2);
				gameOver();
				return true;
			}
			NeedForSpearGame.getInstance().decrementChances();
			int xSphere= (UIController.getInstance().getxLocations()[0] + UIController.getInstance().getxLocations()[1])/2;
			int ySphere= (UIController.getInstance().getyLocations()[0] + UIController.getInstance().getyLocations()[1])/2;
			UIController.getInstance().setSphereX(xSphere);
			UIController.getInstance().setSphereY(ySphere);
			if (NeedForSpearGame.getInstance().getNoblePhantasmIsExpanded()) {
				NeedForSpearGame.getInstance()
						.setNoblePhantasmWidth(NeedForSpearGame.getInstance().getNoblePhantasmWidth() / 2);
				NeedForSpearGame.getInstance().setNoblePhantasmIsExpanded(false);
			}
			
			if (UIController.getInstance().getSphereIsUnstoppable()) {
				UIController.getInstance().setSphereWidth(16);
				UIController.getInstance().setSphereHeight(16);
				UIController.getInstance().setSphereIsUnstoppable(false);
				
			}

		}
		return false;

	}
	
	
	private void gameOver() {
		setTime(0);
	}
	
	
	private void checkPaddleCollision() {

		if (NeedForSpearGame.getInstance().getXnoblePhantasm()
				+ NeedForSpearGame.getInstance().getNoblePhantasmWidth() > 1250) {

			NeedForSpearGame.getInstance()
					.setnoblePhantasmX(1250 - NeedForSpearGame.getInstance().getNoblePhantasmWidth());
		}

		if (NeedForSpearGame.getInstance().getXnoblePhantasm() < 0) {
			NeedForSpearGame.getInstance().setnoblePhantasmX(0);
		}
	}
	
	public void checkReflection() {
		int ballX = NeedForSpearGame.getInstance().getXSphere();
		int ballY = NeedForSpearGame.getInstance().getYSphere();
		Component ball  = new Component() {
		};
		ball.setBounds(ballX, ballY, NeedForSpearGame.getInstance().getSphereWidth(), NeedForSpearGame.getInstance().getSphereHeight());
		Polygon paddle = new Polygon(NeedForSpearGame.getInstance().getxLocations(),NeedForSpearGame.getInstance().getyLocations(),NeedForSpearGame.getInstance().getxLocations().length);
		if(paddle.intersects(ball.getBounds())) {
			NeedForSpearGame.getInstance().setSphereY(UIController.getInstance().getYSphere()-15);
			if(NeedForSpearGame.getInstance().getRotationAngle()!=0) {
				int alpha = NeedForSpearGame.getInstance().getRotationAngle();
				int vy = Math.abs(NeedForSpearGame.getInstance().getSphereYVelocity());
				int vx = Math.abs(NeedForSpearGame.getInstance().getSphereXVelocity());
				double vtotal = Math.sqrt(vx*vx + vy*vy);
				double theta = Math.atan((double)vy/vx)*180/Math.PI;
				double beta = theta + alpha;
				double newAngle = 0;
				int posvy;
				int posvx;
				if(alpha < 0) {
					if(NeedForSpearGame.getInstance().getSphereXVelocity()<0) {
						beta = theta + alpha;
						newAngle = beta + alpha;
						posvy = (int) -(Math.abs(Math.sin((newAngle)*Math.PI/180)) * vtotal);
						posvx = (int) -(Math.abs(Math.cos((newAngle)*Math.PI/180)) * vtotal);

						if(beta + alpha > 90) {
							posvx = - posvx;
						}

					}else {
						beta = theta - alpha;
						newAngle = beta - alpha;
						posvy = (int) -(Math.abs(Math.sin((newAngle)*Math.PI/180)) * vtotal);
						posvx = (int) (Math.abs(Math.cos((newAngle)*Math.PI/180)) * vtotal);

						if(beta - alpha > 90) {
							newAngle = beta + alpha + 2*(90 - beta);
							posvy = (int) -(Math.abs(Math.sin((newAngle)*Math.PI/180)) * vtotal);
							posvx = (int) (Math.abs(Math.cos((newAngle)*Math.PI/180)) * vtotal);
							posvx = - posvx;

						}
					}
				}else {
					if(NeedForSpearGame.getInstance().getSphereXVelocity()<0) {
						beta = alpha + theta;
						newAngle = alpha + beta;
						posvy = (int) -(Math.abs(Math.sin((newAngle)*Math.PI/180)) * vtotal);
						posvx = (int) -(Math.abs(Math.cos((newAngle)*Math.PI/180)) * vtotal);

						if(beta + alpha > 90) {
							newAngle = beta - alpha + 2*(90 - beta);
							posvy = (int) -(Math.abs(Math.sin((newAngle)*Math.PI/180)) * vtotal);
							posvx = (int) (Math.abs(Math.cos((newAngle)*Math.PI/180)) * vtotal);
						}
					}else {
						beta = theta - alpha;
						newAngle = beta - alpha;
						posvy = (int) -(Math.abs(Math.sin((newAngle)*Math.PI/180)) * vtotal);
						posvx = (int) (Math.abs(Math.cos((newAngle)*Math.PI/180)) * vtotal);
						if(beta + alpha > 90) {
							posvx = - posvx;

						}
					}
					
				}
				
				

				NeedForSpearGame.getInstance().setSphereXVelocity(posvx);
				NeedForSpearGame.getInstance().setSphereYVelocity(posvy);
				if(NeedForSpearGame.getInstance().getSphereXVelocity()>0 && !NeedForSpearGame.getInstance().getRotationDirection()) {
					NeedForSpearGame.getInstance().setSphereX(NeedForSpearGame.getInstance().getXSphere()-7);
				}else if((NeedForSpearGame.getInstance().getSphereXVelocity()<0 && NeedForSpearGame.getInstance().getRotationDirection())){
					NeedForSpearGame.getInstance().setSphereX(NeedForSpearGame.getInstance().getXSphere()+7);		
				}
			}else {
				NeedForSpearGame.getInstance().setSphereYVelocity(-NeedForSpearGame.getInstance().getSphereYVelocity());

			}

		}
	}
	
	public void moveBullet() {
		for (Obstacle x : getBullet()) {
			x.setLocation(x.getLocation().getxLoc(), x.getLocation().getyLoc() + 20);
		}
	}
	
	public void moveAbilities() {
		for (Ability x : getAbilityList("ChanceGiving")) {
			x.setLocation(x.getLocation().getxLoc(), x.getLocation().getyLoc() + 10);
		}
		for (Ability x :getAbilityList("MagicalHex")) {
			x.setLocation(x.getLocation().getxLoc(), x.getLocation().getyLoc() + 10);
		}
		for (Ability x : getAbilityList("PaddleExpansion")) {
			x.setLocation(x.getLocation().getxLoc(), x.getLocation().getyLoc() + 10);
		}
		for (Ability x : getAbilityList("UnstoppableSphere")) {
			x.setLocation(x.getLocation().getxLoc(), x.getLocation().getyLoc() + 10);
		}
	}
	
	public void moveLaser() {
		for (Laser x : getLaserList()) {
			x.setLocation(x.getLocation().getxLoc(), x.getLocation().getyLoc() - 20);
		}
	}
	
	private void checkObstacle() {
		Component ball = new Component() {
		};
		
		ball.setBounds(NeedForSpearGame.getInstance().getXSphere(), NeedForSpearGame.getInstance().getYSphere(), NeedForSpearGame.getInstance().getSphereWidth(), NeedForSpearGame.getInstance().getSphereHeight());
		check(ball,toBeDeleted);
	}
	
	private void checkLaserObstacle() {
		int index = 0;
		toBeDeletedLaser[0]= -1;
		toBeDeletedLaser[1]= -1;
		for(Laser laser:laserList) {
			Component l= new Component() {};
			l.setBounds((int)laser.getLocation().getxLoc(),(int)laser.getLocation().getyLoc(), 6, 26);
			check(l,toBeDeletedLaserObs);
			if(toBeDeletedLaserObs[0] != -1) break;
			index++;
		}
		if(toBeDeletedLaserObs[0] != -1) {
			toBeDeletedLaser[0] = (int)laserList.get(index).getLocation().getxLoc();
			toBeDeletedLaser[1] = (int)laserList.get(index).getLocation().getyLoc();
			laserList.remove(index);
		}
		
		
	}
	
	
	private void check(Component comp,int[] arr) {
		arr[0] = -1;
		arr[1] = -1;
		boolean bool = false;
		for(int i=0; i<5; i++) {
			bool = checkHelper(comp, simpleObstacles,arr);
			if(bool==true) break;
			bool = checkHelper(comp, firmObstacles,arr);
			if(bool==true) break;
			bool = checkHelper(comp, expObstacles,arr);
			if(bool==true) break;
			bool = checkHelper(comp, giftObstacles,arr);
			if(bool==true) break;
			bool = checkHelper(comp, hollowPurpleObstacles,arr);
			if(bool==true) break;
		}
		
	}
	
	private boolean checkHelper(Component comp, List<Obstacle> list,int[] arr) {
		int indexRemoved = -1;
		int xCord=0;
		int yCord=0;
		boolean bool = false;
		for (int i = 0; i < list.size(); i++) {
			Obstacle obstacle = list.get(i);
			Component component = new Component() {};
			int xLoc = (int)obstacle.getLocation().getxLoc();
			int yLoc = (int)obstacle.getLocation().getyLoc();
			int width = obstacle.getWidth();
			int height = obstacle.getHeight();
			component.setBounds(xLoc, yLoc, width, height);
			if (component != comp) {
				if (intersects(component, comp) == true) {
					indexRemoved = i;
					xCord= xLoc;
					yCord= yLoc;
					break;
				}
			}
		}
		if(indexRemoved != -1) {
			if (!NeedForSpearGame.getInstance().getSphereIsUnstoppable() && arr != toBeDeletedLaserObs) {
				NeedForSpearGame.getInstance().setSphereYVelocity(-NeedForSpearGame.getInstance().getSphereYVelocity());
			}
			if(list == firmObstacles) {
				if(!list.get(indexRemoved).isInfiniteVoid()) {
					bool = true;
					arr[0] = xCord;
					arr[1] = yCord;
					setItemOfFirmObstacles(indexRemoved);
					this.currentLive = livesOfFirmObstacles.get(indexRemoved);
					if(this.currentLive == 0 || NeedForSpearGame.getInstance().getSphereIsUnstoppable()) {
						UIController.getInstance().setScore(UIController.getInstance().getScore()+100);
						availablePlaces.add(arr);
						list.remove(indexRemoved);
						livesOfFirmObstacles.remove(indexRemoved);
					}	
				}
			}else {
				if(!list.get(indexRemoved).isInfiniteVoid()) {
					
					bool = true;
					arr[0] = xCord;
					arr[1] = yCord;
					list.remove(indexRemoved);
					UIController.getInstance().setScore(UIController.getInstance().getScore()+30);
					if(list==giftObstacles) {
						UIController.getInstance().setScore(UIController.getInstance().getScore()+170);
						createGift(xCord,yCord);
					}
					if(list==expObstacles) {
						UIController.getInstance().setScore(UIController.getInstance().getScore()+20);
						createBullet(xCord,yCord);
					}
					availablePlaces.add(arr);
				}
			}
		}
		return bool;
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
	
	public int[] getToBeDeleted() {
		return toBeDeleted;
	}
	
	public void setToBeDeleted(int[] arr) {
		this.toBeDeleted = arr;
	}
	
	public int[] getToBeDeletedAbility() {
		return toBeDeletedAbility;
	}
	public void setToBeDeletedAbility(int[] toBeDeletedAbility) {
		this.toBeDeletedAbility = toBeDeletedAbility;
	}
	
	public void createGift(int x,int y) {
		Random rand = new Random();
		int number = rand.nextInt(4) + 1;
		switch (number) {
			case 1:
				chanceGiving.add(AbilityFactory.getInstance().createAbility("ChanceGiving",x,y));
				setAbilityType("ChanceGiving");
				break;

			case 2:
				magicalHex.add(AbilityFactory.getInstance().createAbility("MagicalHex",x,y));
				setAbilityType("MagicalHex");
				break;

			case 3:
				unstoppableSphere.add(AbilityFactory.getInstance().createAbility("UnstoppableSphere",x,y));
				setAbilityType("UnstoppableSphere");
				break;

			case 4:
				paddleExpansion.add(AbilityFactory.getInstance().createAbility("PaddleExpansion",x,y));
				setAbilityType("PaddleExpansion");
				break;
			default:
				break;
		}
	}
	
	public void createBullet(int xCord, int yCord) {
		addObstacles("Bullet");
		setLocation("Bullet", xCord, yCord);
	}
	
	public String getAbilityType() {
		return abilityType;
	}
	public void setAbilityType(String abilityType) {
		this.abilityType = abilityType;
	}
	
	private void createLaser() {
		addLaserToList(isLeft);
	}
	
	public int getHollowNumber() {
		return hollowNumber;
	}
	public void setHollowNumber(int hollowNumber) {
		this.hollowNumber = hollowNumber;
	}
	public boolean isHollowControl() {
		return hollowControl;
	}
	public void setHollowControl(boolean hollowControl) {
		this.hollowControl = hollowControl;
	}

	public ArrayList<int[]> getHollowLocs() {
		return hollowLocs;
	}
	public void setHollowLocs(ArrayList<int[]> hollowLocs) {
		this.hollowLocs = hollowLocs;
	}
	public int getObstacle(String type, int x, int y) {
		if (type.equals("WallMaria")) {
			for (int i = 0; i < simpleObstacles.size(); i++) {
				if (simpleObstacles.get(i) != null) {
					if (simpleObstacles.get(i).getLocation().getxLoc() == x
							&& simpleObstacles.get(i).getLocation().getyLoc() == y) {
						return i;
					}
				}
			}
		} else if (type.equals("SteinsGate")) {
			for (int i = 0; i < firmObstacles.size(); i++) {
				if (firmObstacles.get(i) != null) {
					if (firmObstacles.get(i).getLocation().getxLoc() == x
							&& firmObstacles.get(i).getLocation().getyLoc() == y) {
						return i;
					}
				}
			}
		} else if (type.equals("PandorasBox")) {
			for (int i = 0; i < expObstacles.size(); i++) {
				if (expObstacles.get(i) != null) {
					if (expObstacles.get(i).getLocation().getxLoc() == x
							&& expObstacles.get(i).getLocation().getyLoc() == y) {
						return i;
					}
				}
			}
		} else if (type.equals("Bullet")) {
			for (int i = 0; i < bullet.size(); i++) {
				if (bullet.get(i) != null) {
					if (bullet.get(i).getLocation().getxLoc() == x && bullet.get(i).getLocation().getyLoc() == y) {
						return i;
					}
				}
			}
		} else if (type.equals("hollowPurple")) {
			for (int i = 0; i < hollowPurpleObstacles.size(); i++) {
				if (hollowPurpleObstacles.get(i) != null) {
					if (hollowPurpleObstacles.get(i).getLocation().getxLoc() == x
							&& hollowPurpleObstacles.get(i).getLocation().getyLoc() == y) {
						return i;
					}
				}
			}
		} else {
			for (int i = 0; i < giftObstacles.size(); i++) {
				if (giftObstacles.get(i) != null) {
					if (giftObstacles.get(i).getLocation().getxLoc() == x
							&& giftObstacles.get(i).getLocation().getyLoc() == y) {
						return i;
					}
				}
			}

		}
		return -1;
	}
	
	public int[] getToBeDeletedLaser() {
		return toBeDeletedLaser;
	}
	public void setToBeDeletedLaser(int[] toBeDeletedLaser) {
		this.toBeDeletedLaser = toBeDeletedLaser;
	}

	public int[] getToBeDeletedBullet() {
		return toBeDeletedBullet;
	}
	public void setToBeDeletedBullet(int[] toBeDeletedBullet) {
		this.toBeDeletedBullet = toBeDeletedBullet;
	}
	
	public int[] getToBeDeletedLaserObs() {
		return toBeDeletedLaserObs;
	}
	public void setToBeDeletedLaserObs(int[] toBeDeletedLaserObs) {
		this.toBeDeletedLaserObs = toBeDeletedLaserObs;
	}
	
	public int getRemoveObs(int x, int y) {
		for (int i = 0; i < hollowPurpleObstacles.size(); i++) {
			if (hollowPurpleObstacles.get(i) != null) {
				if (hollowPurpleObstacles.get(i).getLocation().getxLoc() == x
						&& hollowPurpleObstacles.get(i).getLocation().getyLoc() == y) {
					removeObs("hollowPurple", i);
					return 0;
				}
			}

		}

		for (int i = 0; i < simpleObstacles.size(); i++) {
			if (simpleObstacles.get(i) != null) {
				if (simpleObstacles.get(i).getLocation().getxLoc() == x
						&& simpleObstacles.get(i).getLocation().getyLoc() == y) {
					removeObs("WallMaria", i);
					setIndexRemoved(i);
					return 1;
				}
			}

		}
		for (int i = 0; i < firmObstacles.size(); i++) {
			if (firmObstacles.get(i) != null) {
				if (firmObstacles.get(i).getLocation().getxLoc() == x
						&& firmObstacles.get(i).getLocation().getyLoc() == y) {
					int lives = this.livesOfFirmObstacles.get(i);
					this.currentLive = this.livesOfFirmObstacles.get(i);
					setIndexRemoved(i);
					if (lives == 1 || NeedForSpearGame.getInstance().getSphereIsUnstoppable()) {
						this.livesOfFirmObstacles.remove(i);
						this.currentLive = 1;
						removeObs("SteinsGate", i);
					} else {
						setItemOfFirmObstacles(i);

					}
					return 2;
				}
			}

		}
		for (int i = 0; i < expObstacles.size(); i++) {
			if (expObstacles.get(i) != null) {
				if (expObstacles.get(i).getLocation().getxLoc() == x
						&& expObstacles.get(i).getLocation().getyLoc() == y) {
					removeObs("PandorasBox", i);
					setIndexRemoved(i);
					return 3;
				}
			}
		}

		for (int i = 0; i < giftObstacles.size(); i++) {
			if (giftObstacles.get(i) != null) {
				if (giftObstacles.get(i).getLocation().getxLoc() == x
						&& giftObstacles.get(i).getLocation().getyLoc() == y) {
					removeObs("GiftBox", i);
					return 4;
				}
			}
		}
		return 5;

	}
	
	public void setIndexRemoved(int indexRemoved) {
		this.indexRemoved = indexRemoved;
	}
	public int getIndexRemoved() {
		return indexRemoved;
	}

	public boolean isPlayChanceSound() {
		return playChanceSound;
	}
	public void setPlayChanceSound(boolean playChanceSound) {
		this.playChanceSound = playChanceSound;
	}
	
	public boolean isExpansionSound() {
		return expansionSound;
	}
	public void setExpansionSound(boolean playChanceSound) {
		this.expansionSound = playChanceSound;
	}
	


}