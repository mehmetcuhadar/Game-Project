package domain.obstacles;

public class Bullet extends Obstacle{
	private int xPos;
	private int yPos;
	
	
	private int yVelocity = 20;
	
	
	
	public Bullet() {
		super("Bullet");	
		this.width=25;
		this.height=20;
	}


	public void move() {
		yPos += yVelocity;
	}
	
	public int getX() {
		
		return this.xPos;
	}
	
	public int getY() {
		
		return this.yPos;
	}
	
	
	
	
	public void setX(int x) {
		
		this.xPos = x;
	}
	
	public void setY(int y) {
		
		this.yPos = y;
	}
	
}
