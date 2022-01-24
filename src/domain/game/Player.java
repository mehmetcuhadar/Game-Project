package domain.game;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import domain.saveLoad.DatabaseSaveLoadAdapter;
import domain.saveLoad.FileSaveLoadAdapter;
import domain.saveLoad.ISaveLoadAdapter;
import ui.UIController;

public class Player {
	private Player() {
	}

	private static Player instance;
	String username;
	double score = 0;
	double health;
	NeedForSpearGame game = NeedForSpearGame.getInstance();
	Settings gameSettings = Settings.getInstance();
	private ISaveLoadAdapter saveLoadAdapter;

	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSaveLoadAdapter(String saveType) {
		if (saveType.equals("Database")) {
			this.saveLoadAdapter = DatabaseSaveLoadAdapter.getInstance();
		} else if (saveType.equals("File")) {
			this.saveLoadAdapter = new FileSaveLoadAdapter();
		}
	}

	private void saveLocations(List<String> saveList, String obsType) {
		if (obsType.equals("SteinsGate")) {
			for (int i = 0; i < gameSettings.getLists(obsType).size(); i++) {
				saveList.add("Obstacle: " + obsType + " x: " + gameSettings.getLists(obsType).get(i).getLocation().getxLoc()
						+ " y: " + gameSettings.getLists(obsType).get(i).getLocation().getyLoc()
						+ " Remaining Chances: " + UIController.getInstance().getItemLive(i));
			}
		} else {
			for (int i = 0; i < gameSettings.getLists(obsType).size(); i++) {
				saveList.add("Obstacle: " + obsType + " x: " + gameSettings.getLists(obsType).get(i).getLocation().getxLoc()
						+ " y: " + gameSettings.getLists(obsType).get(i).getLocation().getyLoc());
			}
		}

	}


	public void loadGame(String username) {
		int WallMariaID = 0;
		int SteinsGateID = 0;
		int PandorasBoxID = 0;
		int GiftBoxID = 0;
		

		ArrayList<String> loadList = new ArrayList<>();
		try {
			loadList = saveLoadAdapter.load(username);
			if (loadList.size() == 0)
				System.out.println("Username does not exist!");
			else {

				String[] timeArr = loadList.get(0).split(":");
				int time = Integer.parseInt(timeArr[1].trim());
				game.setTime(time);

				String[] chancesArr = loadList.get(1).split(":");
				int chance = Integer.parseInt(chancesArr[1].trim());
				
				game.setChances(chance);
				String[] ScoreArr = loadList.get(2).split(":");
				int score = Integer.parseInt(ScoreArr[1].trim());
				game.setScore(score);

				for (int i = 3; i < loadList.size()-2; i++) {
					StringTokenizer tokenizer = new StringTokenizer(loadList.get(i), ": ");
					String type = tokenizer.nextToken().trim();	
					String obsandAbilityType = tokenizer.nextToken().trim();
					String x = tokenizer.nextToken().trim();
					int xLoc = (int) Double.parseDouble(tokenizer.nextToken());
					String y = tokenizer.nextToken().trim();
					int yLoc = (int) Double.parseDouble(tokenizer.nextToken());
					String rem;
					String chan;
					String remainingChances;
					int remChanInt = 0;
			

					if (tokenizer.hasMoreTokens()) {
						rem = tokenizer.nextToken().trim();
						chan = tokenizer.nextToken().trim();
						remainingChances = tokenizer.nextToken().trim();
						remChanInt = Integer.parseInt(remainingChances);
					}
					
					if (type.equals("Obstacle")) {
						
						if (obsandAbilityType.equals("SteinsGate")) {		
							game.addObstacleTolist(obsandAbilityType);
							game.setObstacleID(SteinsGateID, obsandAbilityType);
							game.setLocationToObstacle(obsandAbilityType, xLoc, yLoc);
							game.initializeLivesOfFirmObstacles(SteinsGateID, remChanInt);
							SteinsGateID++;

						} else if (obsandAbilityType.equals("WallMaria")) {
							game.addObstacleTolist(obsandAbilityType);
							game.setObstacleID(WallMariaID, obsandAbilityType);
							game.setLocationToObstacle(obsandAbilityType, xLoc, yLoc);
							WallMariaID++;

						} else if (obsandAbilityType.equals("PandorasBox")) {
							game.addObstacleTolist(obsandAbilityType);
							game.setObstacleID(PandorasBoxID, obsandAbilityType);
							game.setLocationToObstacle(obsandAbilityType, xLoc, yLoc);
							PandorasBoxID++;

						} else {
							game.addObstacleTolist(obsandAbilityType);
							game.setObstacleID(GiftBoxID, obsandAbilityType);
							game.setLocationToObstacle(obsandAbilityType, xLoc, yLoc);
							GiftBoxID++;
						}
					}

				}
				for (int i = loadList.size()-2; i < loadList.size(); i++) {
					StringTokenizer tokenizer = new StringTokenizer(loadList.get(i), ":");
					String type = tokenizer.nextToken();
					Boolean bool = Boolean.parseBoolean(tokenizer.nextToken().trim());
					if(i==loadList.size()-2) {
						game.setNoblePhantasmIsExpanded(bool);
						
					}else {
						game.setSphereIsUnstoppable(bool);
						
					
					}
					
				}

			}
		} catch (IOException e) {
			System.out.println("Username does not exist!");
		}
		
		
		

	}

	public void saveGame(String username) {
		ArrayList<String> saveList = new ArrayList<>();
		saveList.add("Time: " + UIController.getInstance().getTime());
		saveList.add("Chances: " + UIController.getInstance().getChances());
		saveList.add("Score: " + UIController.getInstance().getScore());
		saveLocations(saveList, "WallMaria");
		saveLocations(saveList, "SteinsGate");
		saveLocations(saveList, "PandorasBox");
		saveLocations(saveList, "GiftBox");
		String isExpanded=""+game.getNoblePhantasmIsExpanded();
		String isUnstoppable=""+game.getSphereIsUnstoppable();
		saveList.add("Noble phantasm is expanded?: "+isExpanded);
		saveList.add("Enchanted sphere is unstoppable?: " +isUnstoppable);
		
	
		try {
			saveLoadAdapter.save(saveList, username);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}