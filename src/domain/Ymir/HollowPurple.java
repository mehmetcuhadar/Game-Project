package domain.Ymir;

import domain.obstacles.Obstacle;

public class HollowPurple extends Obstacle{
	private double length;
	

	public HollowPurple() {
		super("hollowPurple");
		type = "hollowPurple";
		imagePath="src/images/hollow_purple.png";
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
