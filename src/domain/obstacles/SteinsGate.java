package domain.obstacles;

import java.util.Random;

public class SteinsGate extends Obstacle{
	private double length;
	private int remainingChances;
	


	//firm obstacle
	public SteinsGate() {
		super("SteinsGate");
		this.remainingChances = 3;
		imagePath="src/images/firm_obstacle.png";
		type="SteinsGate";
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

	public int getRemainingChances() {
		return remainingChances;
	}

	public void setRemainingChances(int remainingChances) {
		this.remainingChances = remainingChances;
	}
	
	

}
