package domain.obstacles;

import java.util.Random;

import ui.UIController;

public class WallMaria extends Obstacle{
	private double length;
	
	//simple obstacle
	public WallMaria() {
		super("WallMaria");
		type = "WallMaria";
		imagePath="src/images/simple_obstacle.png";
		Random rand = new Random();
		this.width=25;
		this.height=20;
		
		int probOfMovement = rand.nextInt(4);
		if(probOfMovement!=1) {
			this.isStiff = true;
		}
	}
	
	


	public double getLength() {
		return length;
	}


	public void setLength(double length) {
		this.length = length;
	}
	
	public void explode() {
		
	}

}
