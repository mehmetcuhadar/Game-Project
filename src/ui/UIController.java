package ui;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import domain.game.NeedForSpearGame;


public class UIController {
	private UIController() {
	}

	private static UIController instance;

	public static UIController getInstance() {
		if (instance == null) {
			instance = new UIController();
		}
		return instance;
	}

	public void setTime(int time) {
		NeedForSpearGame.getInstance().setTime(time);
	}

	public int getTime() {
		return NeedForSpearGame.getInstance().getTime();
	}
	public int getNumOfSavedGames() {
		return NeedForSpearGame.getInstance().getNumOfSavedGames();
	}

	public void setNumOfSavedGames(int numOfSavedGames) {
		NeedForSpearGame.getInstance().setTime(numOfSavedGames);
		
	}
	public void setSaveLoadAdapter(String saveType) {
		NeedForSpearGame.getInstance().setSaveLoadAdapter(saveType);
	}
	public boolean logIn(String username, String password) {
		return NeedForSpearGame.getInstance().logIn(username, password);
	}

	public boolean accountExists(String username) {
		return NeedForSpearGame.getInstance().accountExists(username);
	}

	public void createAccount(String name, String surname, String username, String password) {
		NeedForSpearGame.getInstance().createAccount(name, surname, username, password);
	}
	
	
	public void moveObs() {
		NeedForSpearGame.getInstance().moveObs();
	}
	
	public boolean isFirmStiff(int index) {
		return NeedForSpearGame.getInstance().isFirmStiff(index);
	}
	
	public boolean isSimpleStiff(int index) {
		return NeedForSpearGame.getInstance().isSimpleStiff(index);
	}

	public void setUsername(String username) {
		NeedForSpearGame.getInstance().setUsername(username);
	}

	public String getUsername() {
		return NeedForSpearGame.getInstance().getUsername();
	}

	public ArrayList<String> getSaveList() {
		return NeedForSpearGame.getInstance().getSaveList();
	}
	public ArrayList<String> getLoadList() {
		return NeedForSpearGame.getInstance().getLoadList();
	}

	public int getLiveOfObstacle() {
		return NeedForSpearGame.getInstance().getLiveOfObstacle();
	}

	public int getItemLive(int index) {
		return NeedForSpearGame.getInstance().getItemLive(index);
	}

	public void setLiveOfObstacle(int index) {
		NeedForSpearGame.getInstance().setLiveOfObstacle(index);
	}

	public void addObstacleTolist(String type,int width,int height) {
		NeedForSpearGame.getInstance().addObstacleTolist(type);
	}

	public void setLocationToObstacle(String type, int x, int y) {
		NeedForSpearGame.getInstance().setLocationToObstacle(type, x, y);
	}

	public void updateLocation(String type, int id, int x, int y) {
		NeedForSpearGame.getInstance().updateLocation(type, id, x, y);
	}

	public void removelocation(String type, int id) {
		NeedForSpearGame.getInstance().removelocation(type, id);
	}

	public static void setObstacleID(int id, String type) {
		NeedForSpearGame.getInstance().setObstacleID(id, type);
	}
	
	public boolean getGameStopped() {
		return NeedForSpearGame.getInstance().getGameStopped();
	}

	
	// Bullet Functions

	

	public int removeBullet(int x, int y) {
		return NeedForSpearGame.getInstance().removeBullet(x, y);
	}
	public void resetgameLists() {
		NeedForSpearGame.getInstance().resetgameLists();
	}
	public void resetSaveLoadLists() {
		NeedForSpearGame.getInstance().resetSaveLoadLists();
	}


	// Sphere Functions

	public void initiliazeSphere(int x, int y, int width, int height, Image img) {
		NeedForSpearGame.getInstance().initiliazeSphere(x, y, width, height, img);
	}

	public void moveSphere() {

		NeedForSpearGame.getInstance().moveSphere();
	}

	public int getXSphere() {

		return NeedForSpearGame.getInstance().getSphere().getX();
	}

	public int getYSphere() {

		return NeedForSpearGame.getInstance().getSphere().getY();
	}

	public int getSphereXVelocity() {

		return NeedForSpearGame.getInstance().getSphere().getXVelocity();
	}

	public int getSphereYVelocity() {

		return NeedForSpearGame.getInstance().getSphere().getYVelocity();
	}

	public void setSphereXVelocity(int xVel) {

		NeedForSpearGame.getInstance().getSphere().setXVelocity(xVel);
	}

	public void setSphereYVelocity(int yVel) {

		NeedForSpearGame.getInstance().getSphere().setYVelocity(yVel);
	}

	public void setSphereX(int x) {

		NeedForSpearGame.getInstance().getSphere().setX(x);
	}

	public void setY(int y) {

		NeedForSpearGame.getInstance().getSphere().setY(y);
	}

	public int getSphereWidth() {

		return NeedForSpearGame.getInstance().getSphereWidth();
	}
	
	public void setSphereWidth(int width) {
		NeedForSpearGame.getInstance().setSphereWidth(width);
	}

	public int getSphereHeight() {

		return NeedForSpearGame.getInstance().getSphereHeight();
	}
	
	public void setSphereHeight(int height) {
		NeedForSpearGame.getInstance().setSphereHeight(height);
	}
	// Noble Phantasm Functions

	public void initializeNoblePhantasm(int x, int y, int width, int height, Image img) {
		NeedForSpearGame.getInstance().initiliazeNoblePhantasm(x, y, width, height, img);
	}

	public void movenoblePhantasm() {

		NeedForSpearGame.getInstance().movenoblePhantasm();
	}

	public int getXnoblePhantasm() {

		return NeedForSpearGame.getInstance().getNoblePhantasm().getX();
	}

	public int getYnoblePhantasm() {

		return NeedForSpearGame.getInstance().getNoblePhantasm().getY();
	}

	public int getnoblePhantasmXVelocity() {

		return NeedForSpearGame.getInstance().getNoblePhantasm().getXVelocity();
	}

	public void setnoblePhantasmXVelocity(int xVel) {

		NeedForSpearGame.getInstance().getNoblePhantasm().setXVelocity(xVel);
	}

	public void setnoblePhantasmX(int x) {

		NeedForSpearGame.getInstance().getNoblePhantasm().setX(x);
	}


	public int getNoblePhantasmWidth() {

		return NeedForSpearGame.getInstance().getNoblePhantasmWidth();
	}

	public int getNoblePhantasmHeight() {

		return NeedForSpearGame.getInstance().getNoblePhantasmHeight();
	}

	

	

	public void saveGame(ArrayList<String> saveList, String username) throws IOException {
		NeedForSpearGame.getInstance().saveGame(saveList, username);
	}
	public void loadGame(ArrayList<String> loadList,String username) throws IOException {
		NeedForSpearGame.getInstance().loadGame(loadList, username);
	}

	public int getScore() {
		return NeedForSpearGame.getInstance().getScore();
	}

	public void setScore(int x) {
		NeedForSpearGame.getInstance().setScore(x);
	}

	public int getChances() {
		return NeedForSpearGame.getInstance().getChances();
	}
	public void setChances(int chances) {
		NeedForSpearGame.getInstance().setChances(chances);
	}

	public void decrementChances() {
		NeedForSpearGame.getInstance().decrementChances();
	
	}

	public void incrementChances() {
		NeedForSpearGame.getInstance().incrementChances();
	
	}
	
	public int getRemoveAbility(String type,int x, int y) {
		return NeedForSpearGame.getInstance().getRemoveAbility(type,x, y);
	}
	
	public void setNoblePhantasmWidth(int width) {
		NeedForSpearGame.getInstance().setNoblePhantasmWidth(width);
	}

	
	public void removeLaser(int x) {
		 NeedForSpearGame.getInstance().removeLaser(x);
	}

	public void addLaserToList(boolean isLeft) {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().addLaserToList(isLeft);
	}


	public double getLaserX(int index) {
		return NeedForSpearGame.getInstance().getLaserX(index);
	}
	
	public double getLaserY(int index) {
		return NeedForSpearGame.getInstance().getLaserY(index);
	}
	
	public void removeBulletItem(int x) {
		 NeedForSpearGame.getInstance().removeBulletItem(x);
	}
	
	public void removeChance(int x) {
		 NeedForSpearGame.getInstance().removeChance(x);
	}
	
	public void removeUnsSphere(int x) {
		 NeedForSpearGame.getInstance().removeUnsSphere(x);
	}
	
	public void removePaddleExp(int x) {
		 NeedForSpearGame.getInstance().removePaddleExp(x);
	}
	
	public void removeMagical(int x) {
		 NeedForSpearGame.getInstance().removeMagical(x);
	}
	
	public boolean getSphereIsUnstoppable() {
		return NeedForSpearGame.getInstance().getSphereIsUnstoppable();
	}
	
	public void setSphereIsUnstoppable(boolean bool) {
		NeedForSpearGame.getInstance().setSphereIsUnstoppable(bool);;
	}
	
	public boolean getNoblePhantasmIsExpanded() {
		return NeedForSpearGame.getInstance().getNoblePhantasmIsExpanded();
	}
	
	public void setNoblePhantasmIsExpanded(boolean bool) {
		NeedForSpearGame.getInstance().setNoblePhantasmIsExpanded(bool);
	}

	public void setSphereY(int i) {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().setSphereY(i);
	}
	public String ymirRandom() {
		return NeedForSpearGame.getInstance().ymirRandom();
	}
	public void setDoubleAccel(boolean bool) {
		NeedForSpearGame.getInstance().setDoubleAccel(bool);
	}
	public boolean getDoubleAccel() {		
		return NeedForSpearGame.getInstance().getDoubleAccel();
	}
	public void startInfiniteVoid() {
		NeedForSpearGame.getInstance().startInfiniteVoid();
	}
	public void finishInfiniteVoid() {
		NeedForSpearGame.getInstance().finishInfiniteVoid();
	}
	public ArrayList<int[]> getAvailablePlaces() {
		return NeedForSpearGame.getInstance().getAvailablePlaces();
	}
	
	

	public void setRotationAngle() {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().setRotationAngle();
		
	}

	public void setRotationDirection(boolean b) {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().setRotationDirection(b);

	}

	public boolean getRotateDirection() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getRotationDirection();
	}

	public int getRotationAngle() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getRotationAngle();
	}
	
	public void setNoblePhantasmLoc(int angle) {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().setnoblePhantasmLoc(angle);
		
	}

	public void setNoblePhantasmHeight(int i) {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().setnoblePhantasmHeight(i);
	}
	
	public boolean checkName(String name) {
		// TODO Auto-generated method stub
		return name.isEmpty();
	}

	public boolean checkSurname(String surname) {
		// TODO Auto-generated method stub
		return surname.isEmpty();
	}

	public boolean checkUsername(String username) {
		// TODO Auto-generated method stub
		return username.isEmpty();
	}

	public boolean checkPassword(String password) {
		// TODO Auto-generated method stub
		return password.isEmpty();
	}

	public int[] getxLocations() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getxLocations();
	}

	public int[] getyLocations() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getyLocations();

	}

	public void setxLocations(int[] arr) {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().setxLocations(arr);
	}

	public void setyLocations(int[] arr) {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().setyLocations(arr);
	}

	public void startGameLogic() {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().startGameLogic();
	}

	public boolean checkBallCollision() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().checkBallCollision();
	}

	public int[] getToBeDeleted() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getToBeDeleted();
	}
	
	public void setToBeDeleted(int[] arr) {
		NeedForSpearGame.getInstance().setToBeDeleted(arr);
	}

	public void setGameStopped(boolean b) {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().setGameStopped(b);
	}
	
	

	public String getAbilityType() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getAbilityType();
	}

	public int[] getToBeDeletedAbility() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getToBeDeletedAbility();
	}

	public boolean isPutLaser() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().isPutLaser();
	}

	public void setPutLaser(boolean b) {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().setPutLaser(b);
	}
	
	public ArrayList<int[]> getHollowLocs() {
		return NeedForSpearGame.getInstance().getHollowLocs();
	}
	public void setHollowLocs(ArrayList<int[]> hollowLocs) {
		NeedForSpearGame.getInstance().setHollowLocs(hollowLocs);
	}

	public int getObstacleID(String string, int oldx, int oldy) {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getObstacleID(string, oldx, oldy);
	}

	public int[] getToBeDeletedLaser() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getToBeDeletedLaser();
	}

	public int[] getToBeDeletedBullet() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getToBeDeletedBullet();
	}

	public int[] getToBeDeletedLaserObs() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getToBeDeletedLaserObs();
	}

	public int getRemoveObs(int x, int y) {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getRemoveObs(x,y);
	}

	public int getIndexRemoved() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getIndexRemoved();
	}

	public int getGameFinished() {
		// TODO Auto-generated method stub
		return NeedForSpearGame.getInstance().getGameFinished();
	}

	public void setGameFinished(int gameFinished) {
		NeedForSpearGame.getInstance().setGameFinished(gameFinished);
	}

	public boolean isPlayChanceSound() {
		return NeedForSpearGame.getInstance().isPlayChanceSound();
	}

	public void setPlayChanceSound(boolean b) {
		NeedForSpearGame.getInstance().setPlayChanceSound(b);
	}

	public boolean isExpansionSound() {
		return NeedForSpearGame.getInstance().isExpansionSound();
	}

	public void setExpansionSound(boolean b) {
		NeedForSpearGame.getInstance().setExpansionSound(b);
	}

	public int getAbilityxLoc(String string,int index) {
		return NeedForSpearGame.getInstance().getAbilityxLoc(string,index);
	}

	public int getAbilityyLoc(String string,int index) {
		return NeedForSpearGame.getInstance().getAbilityyLoc(string,index);
	}

	public int getObstaclexLoc(String string, int t) {
		return NeedForSpearGame.getInstance().getObstaclexLoc(string,t);
	}

	public int getObstacleyLoc(String string, int t) {
		return NeedForSpearGame.getInstance().getObstacleyLoc(string,t);
	}

	public int getListSize(String type) {
		return NeedForSpearGame.getInstance().getListSize(type);
	}

	public void initializeNumOfSavedGames() {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().initializeNumOfSavedGames();
	}

	public void setSelectedSavedGame(String selected) {
		// TODO Auto-generated method stub
		NeedForSpearGame.getInstance().setSelectedSavedGame(selected);
	}
	



}