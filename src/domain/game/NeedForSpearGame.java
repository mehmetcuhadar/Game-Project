package domain.game;

import static com.mongodb.client.model.Filters.eq;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import domain.Ymir.Ymir;
import domain.magicalAbilities.Ability;
import domain.obstacles.Obstacle;
import domain.playerObjects.EnchantedSphere;
import domain.playerObjects.NoblePhantasm;
import domain.saveLoad.DatabaseSaveLoadAdapter;

public class NeedForSpearGame {
	private NeedForSpearGame() {
	}

	private static NeedForSpearGame instance;
	private static Player player = Player.getInstance();
	public static Settings settings = Settings.getInstance();
	EnchantedSphere sphere;
	NoblePhantasm noblePhantasm;
	private int score;
	private int chances = 3;
	private boolean gameStopped = false;
	private int gameFinished = 0;


	public static NeedForSpearGame getInstance() {
		if (instance == null) {
			instance = new NeedForSpearGame();
		}
		return instance;
	}

	public void setTime(int time) {
		settings.setTime(time);
	}

	public int getTime() {
		return settings.getTime();
	}

	public int getNumOfSavedGames() {
		return settings.getNumOfSavedGames();
	}

	public void setNumOfSavedGames(int numOfSavedGames) {
		settings.setNumOfSavedGames(numOfSavedGames);

	}

	@SuppressWarnings("static-access")
	public void initializeNumOfSavedGames() {
		String username = getUsername();
		Document my_doc = DatabaseSaveLoadAdapter.getInstance().getCollection().find(eq("user_name", username)).first();
		int docSize = my_doc.size();
		System.out.println(docSize);
		setNumOfSavedGames(docSize - 5);

	}

	public String getSelectedSavedGame() {
		return settings.getSelectedSavedGame();
	}
	

	public void setSaveLoadAdapter(String saveType) {
		player.setSaveLoadAdapter(saveType);
	}

	public void setSelectedSavedGame(String selectedSavedGame) {
		settings.setSelectedSavedGame(selectedSavedGame);
	}

	public void setUsername(String username) {
		player.setUsername(username);
	}

	public String getUsername() {
		return player.getUsername();
	}

	public ArrayList<String> getSaveList() {
		return Settings.getInstance().getSaveList();
	}

	public ArrayList<String> getLoadList() {
		return Settings.getInstance().getLoadList();
	}

	public Obstacle createObstacle(String type) {

		return ObstacleFactory.getInstance().createObstacle(type);

	}

	public List<Obstacle> Lists(String type) {
		return Settings.getInstance().getLists(type);

	}

	public int getLiveOfObstacle() {
		return Settings.getInstance().getLivesOfFirmObstacles();
	}

	public boolean isFirmStiff(int index) {
		return Settings.getInstance().isFirmStiff(index);
	}
	
	public boolean isSimpleStiff(int index) {
		return Settings.getInstance().isSimpleStiff(index);
	}
	
	public void moveObs() {
		Settings.getInstance().moveObs();
	}
	
	public int getItemLive(int index) {
		return Settings.getInstance().getItemLive(index);
	}

	public void setLiveOfObstacle(int index) {
		Settings.getInstance().setItemOfFirmObstacles(index);
	}

	public void initializeLivesOfFirmObstacles(int id, int index) {
		Settings.getInstance().initializeLivesOfFirmObstacles(id, index);
	}

	public void addObstacleTolist(String type) {
		Settings.getInstance().addObstacles(type);

	}

	public void setLocationToObstacle(String type, int x, int y) {
		Settings.getInstance().setLocation(type, x, y);

	}

	public void removelocation(String type, int id) {

		Settings.getInstance().removeObs(type, id);
	}

	public void updateLocation(String type, int id, int x, int y) {
		Settings.getInstance().updateLocation(type, id, x, y);
	}

	public void setObstacleID(int id, String type) {
		Settings.getInstance().setObstacleID(id, type);
	}

	
	public void setGameStopped(boolean bool) {
		this.gameStopped = bool;
	}
	
	public boolean getGameStopped() {
		return this.gameStopped;
	}

	public int removeBullet(int x, int y) {
		return Settings.getInstance().removeBullet(x, y);
	}

	public void resetgameLists() {
		Settings.getInstance().resetgameLists();
	}
	public void resetSaveLoadLists() {
		Settings.getInstance().resetSaveLoadLists();
	}


	public boolean logIn(String username, String password) {
		DatabaseSaveLoadAdapter.getInstance().dbConn();
		return DatabaseSaveLoadAdapter.getInstance().logIn(username, password);
	}

	public boolean accountExists(String username) {
		DatabaseSaveLoadAdapter.getInstance().dbConn();
		return DatabaseSaveLoadAdapter.getInstance().accountExists(username);
	}

	public void createAccount(String name, String surname, String username, String password) {
		DatabaseSaveLoadAdapter.getInstance().dbConn();
		DatabaseSaveLoadAdapter.getInstance().createAccount(name, surname, username, password);
	}

	public void saveGame(ArrayList<String> saveList, String username) throws IOException {
		Player.getInstance().saveGame(username);
	}

	public void loadGame(ArrayList<String> loadList, String username) throws IOException {
		Player.getInstance().loadGame(username);
	}

	// Sphere Functions

	public EnchantedSphere getSphere() {
		return sphere;
	}

	public void initiliazeSphere(int x, int y, int width, int height, Image img) {
		sphere = new EnchantedSphere(x, y, width, height, img);
	}

	

	public void moveSphere() {

		sphere.move();
	}

	public int getXSphere() {

		return sphere.getX();
	}

	public int getYSphere() {

		return sphere.getY();
	}

	public int getSphereXVelocity() {

		return sphere.getXVelocity();
	}

	public int getSphereYVelocity() {

		return sphere.getYVelocity();
	}

	public void setSphereXVelocity(int xVel) {

		sphere.setXVelocity(xVel);
	}

	public void setSphereYVelocity(int yVel) {

		sphere.setYVelocity(yVel);
	}

	public void setSphereX(int x) {

		sphere.setX(x);
	}

	public void setY(int y) {

		sphere.setY(y);
	}

	public int getSphereWidth() {

		return sphere.getWidth();
	}

	public int getSphereHeight() {

		return sphere.getHeight();
	}

	// Noble Phantasm Functions

	public NoblePhantasm getNoblePhantasm() {
		return noblePhantasm;
	}

	public void initiliazeNoblePhantasm(int x, int y, int width, int height, Image img) {
		noblePhantasm = new NoblePhantasm(x, y, width, height, img);
	}

	public void movenoblePhantasm() {

		noblePhantasm.move();
	}

	public int getXnoblePhantasm() {

		return noblePhantasm.getX();
	}

	public int getYnoblePhantasm() {

		return noblePhantasm.getY();
	}

	public int getnoblePhantasmXVelocity() {

		return noblePhantasm.getXVelocity();
	}

	public void setnoblePhantasmXVelocity(int xVel) {

		noblePhantasm.setXVelocity(xVel);
	}

	public void setnoblePhantasmX(int x) {

		noblePhantasm.setX(x);
	}
	

	public int getNoblePhantasmWidth() {

		return noblePhantasm.getWidth();
	}

	public int getNoblePhantasmHeight() {

		return noblePhantasm.getHeight();
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getChances() {
		return this.chances;
	}

	public void setChances(int chances) {
		this.chances = chances;
	}

	public void decrementChances() {
		if(this.chances>0) {
			this.chances -= 1;
		}
		
	}

	public void incrementChances() {
		if(this.chances<5) {
			this.chances += 1;
		}
		
	}

	public List<Ability> getAbilityList(String type) {
		return Settings.getInstance().getAbilityList(type);

	}

	
	public int getRemoveAbility(String type, int x, int y) {
		// TODO Auto-generated method stub
		return Settings.getInstance().getRemoveAbility(type, x, y);
	}

	public void setNoblePhantasmWidth(int width) {
		// TODO Auto-generated method stub
		noblePhantasm.setWidth(width);
	}

	
	
	public void removeLaser(int x) {
		 Settings.getInstance().removeLaser(x);
	}

	public void addLaserToList(boolean isLeft) {
		// TODO Auto-generated method stub
		Settings.getInstance().addLaserToList(isLeft);
	}


	public double getLaserX(int index) {
		return Settings.getInstance().getLaserX(index);
	}

	public double getLaserY(int index) {
		return Settings.getInstance().getLaserY(index);
	}

	public void setSphereWidth(int width) {
		// TODO Auto-generated method stub
		sphere.setWidth(width);
		
	}

	public void setSphereHeight(int height) {
		// TODO Auto-generated method stub
		sphere.setHeight(height);
	}

	public void removeBulletItem(int x) {
		// TODO Auto-generated method stub
		Settings.getInstance().removeBulletItem(x);
	}

	public void removeChance(int x) {
		// TODO Auto-generated method stub
		Settings.getInstance().removeChance(x);
	}

	public void removeUnsSphere(int x) {
		// TODO Auto-generated method stub
		Settings.getInstance().removeUnsSphere(x);
	}

	public void removePaddleExp(int x) {
		// TODO Auto-generated method stub
		Settings.getInstance().removePaddleExp(x);
	}

	public void removeMagical(int x) {
		// TODO Auto-generated method stub
		Settings.getInstance().removeMagical(x);
	}
	
	public boolean getSphereIsUnstoppable() {
		return settings.isUnstoppable();
	}
	
	public void setSphereIsUnstoppable(boolean bool) {
		settings.setIsUnstoppable(bool);
	}
	
	public boolean getNoblePhantasmIsExpanded() {
		return settings.isExpanded();
	}
	
	public void setNoblePhantasmIsExpanded(boolean bool) {
		settings.setExpanded(bool);
	}

	public void setSphereY(int i) {
		// TODO Auto-generated method stub
		sphere.setY(i);
	}

	public String ymirRandom() {
		return Ymir.getInstance().generateRandomAbilities();
	}
	
	public void setDoubleAccel(boolean bool) {
		settings.setDoubleAccel(bool);
	}
	public boolean getDoubleAccel() {		
		return settings.getDoubleAccel();
	}
	public ArrayList<int[]> getAvailablePlaces() {
		return settings.getAvailablePlaces();
	}

	public void setRotationAngle() {
		// TODO Auto-generated method stub
		noblePhantasm.setRotationAngle();
		
	}

	public void setRotationDirection(boolean b) {
		// TODO Auto-generated method stub
		noblePhantasm.setRotationDirection(b);
	}

	public boolean getRotationDirection() {
		// TODO Auto-generated method stub
		return noblePhantasm.getRotationDirection();
	}

	public int getRotationAngle() {
		// TODO Auto-generated method stub
		return noblePhantasm.getRotationAngle();
	}

	public void setnoblePhantasmLoc(int angle) {
		// TODO Auto-generated method stub
		noblePhantasm.setnoblePhantasmLoc(angle);
	}

	public void setnoblePhantasmHeight(int i) {
		// TODO Auto-generated method stub
		noblePhantasm.setnoblePhantasmHeight(i);
	}
	public void startInfiniteVoid() {
		settings.startInfiniteVoid();
	}
	public void finishInfiniteVoid() {
		settings.finishInfiniteVoid();
	}

	public int[] getxLocations() {
		// TODO Auto-generated method stub
		return noblePhantasm.getxLocations();
	}

	public int[] getyLocations() {
		// TODO Auto-generated method stub
		return noblePhantasm.getyLocations();
	}

	public void setxLocations(int[] arr) {
		// TODO Auto-generated method stub
		noblePhantasm.setxLocations(arr);
	}

	public void setyLocations(int[] arr) {
		// TODO Auto-generated method stub
		noblePhantasm.setyLocations(arr);
	}

	public void startGameLogic() {
		// TODO Auto-generated method stub
		settings.startGameLogic();
	}

	public boolean checkBallCollision() {
		// TODO Auto-generated method stub
		return settings.checkBallCollision();
	}

	public int[] getToBeDeleted() {
		// TODO Auto-generated method stub
		return Settings.getInstance().getToBeDeleted();
	}
	
	public void setToBeDeleted(int[] arr) {
		Settings.getInstance().setToBeDeleted(arr);
	}

	

	

	public String getAbilityType() {
		return Settings.getInstance().getAbilityType();
	}

	public int[] getToBeDeletedAbility() {
		// TODO Auto-generated method stub
		return Settings.getInstance().getToBeDeletedAbility();
	}

	public boolean isPutLaser() {
		// TODO Auto-generated method stub
		return Settings.getInstance().isPutLaser();
	}

	public void setPutLaser(boolean b) {
		// TODO Auto-generated method stub
		Settings.getInstance().setPutLaser(b);
	}

	public ArrayList<int[]> getHollowLocs() {
		return Settings.getInstance().getHollowLocs();
	}
	public void setHollowLocs(ArrayList<int[]> hollowLocs) {
		Settings.getInstance().setHollowLocs(hollowLocs);
	}

	public int getObstacleID(String string, int oldx, int oldy) {
		// TODO Auto-generated method stub
		return Settings.getInstance().getObstacle(string, oldx, oldy);
	}

	public int[] getToBeDeletedLaser() {
		// TODO Auto-generated method stub
		return Settings.getInstance().getToBeDeletedLaser();
	}

	public int[] getToBeDeletedBullet() {
		// TODO Auto-generated method stub
		return Settings.getInstance().getToBeDeletedBullet();
	}

	public int[] getToBeDeletedLaserObs() {
		// TODO Auto-generated method stub
		return Settings.getInstance().getToBeDeletedLaserObs();
	}

	public int getRemoveObs(int x, int y) {
		// TODO Auto-generated method stub
		return Settings.getInstance().getRemoveObs(x, y);
	}

	public int getIndexRemoved() {
		// TODO Auto-generated method stub
		return Settings.getInstance().getIndexRemoved();
	}

	public int getGameFinished() {
		// TODO Auto-generated method stub
		return this.gameFinished;
	}
	
	public void setGameFinished(int gameFinished) {
		this.gameFinished = gameFinished;
	}

	public boolean isPlayChanceSound() {
		return Settings.getInstance().isPlayChanceSound();
	}

	public void setPlayChanceSound(boolean b) {
		Settings.getInstance().setPlayChanceSound(b);
	}

	public boolean isExpansionSound() {
		// TODO Auto-generated method stub
		return Settings.getInstance().isExpansionSound();
	}

	public void setExpansionSound(boolean b) {
		// TODO Auto-generated method stub
		Settings.getInstance().setExpansionSound(b);
	}

	public int getAbilityxLoc(String string, int index) {
		// TODO Auto-generated method stub
		return (int) getAbilityList(string).get(index).getLocation().getxLoc();
	}

	public int getAbilityyLoc(String string, int index) {
		// TODO Auto-generated method stub
		return (int) getAbilityList(string).get(index).getLocation().getyLoc();
	}

	public int getObstaclexLoc(String string, int t) {
		// TODO Auto-generated method stub
		return (int) Lists(string).get(t).getLocation().getxLoc();
	}

	public int getObstacleyLoc(String string, int t) {
		// TODO Auto-generated method stub
		return (int) Lists(string).get(t).getLocation().getyLoc();
	}

	public int getListSize(String type) {
		// TODO Auto-generated method stub
		return Lists(type).size();
	}
	

}