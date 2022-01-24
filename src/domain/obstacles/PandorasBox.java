package domain.obstacles;

import java.util.Random;

public class PandorasBox extends Obstacle{
	private double radius;
	private boolean isStiff;
	private boolean isMovable;


	


	//explosive obstacle
	public PandorasBox() {
		super("PandorasBox");
		imagePath="src/images/explosive_obstacle.png";
		type="PandorasBox";
		this.width=15;
		this.height=15;
	}
	
	
	public double getRadius() {
		return radius;
	}



	public void setRadius(double radius) {
		this.radius = radius;
	}

	public boolean isStiff() {
		return isStiff;
	}


	public void setStiff(boolean isStiff) {
		this.isStiff = isStiff;
	}

	public boolean isMovable() {
		return isMovable;
	}

	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}
	
	
	
}
