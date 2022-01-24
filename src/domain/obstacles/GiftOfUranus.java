package domain.obstacles;

import ui.UIController;

public class GiftOfUranus extends Obstacle{
	private double length;
	
	//gift obstacle
	public GiftOfUranus() {
		super("GiftOfUranus");
		imagePath="src/images/gift_obstacle.png";
		type="GiftOfUranus";
		this.width=25;
		this.height=20;
	}
	
	
	



	public double getLength() {
		return length;
	}



	public void setLength(double length) {
		this.length = length;
	}


}
