package domain.game;


import domain.Ymir.HollowPurple;
import domain.obstacles.Bullet;
import domain.obstacles.GiftOfUranus;
import domain.obstacles.Obstacle;
import domain.obstacles.PandorasBox;
import domain.obstacles.SteinsGate;
import domain.obstacles.WallMaria;

public class ObstacleFactory {
	private static ObstacleFactory instance;  

	private ObstacleFactory() {}  

	public static ObstacleFactory getInstance() {  
		if (instance == null) {  
			instance = new ObstacleFactory();  
		}  
		return instance;  
	}

	public Obstacle createObstacle(String type) throws NullPointerException  {
		if(type.equals("SteinsGate"))return new SteinsGate();
		else if(type.equals("PandorasBox"))return new PandorasBox();
		else if(type.equals("WallMaria"))return new WallMaria();
		else if(type.equals("Bullet"))return new Bullet();
		else if(type.equals("hollowPurple"))return new HollowPurple();
		else if (type.equals("GiftBox"))return new GiftOfUranus();
		return null;

	}
	
	public void setLocation(Obstacle obs, int x , int y) {
		obs.setLocation(x, y);
	}
	
	

}