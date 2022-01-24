package domain.obstacles;

import domain.game.Location;

public abstract class Obstacle {
	
	private Location location;
	private double xSpeed = 0;
	private double ySpeed;
	public String imagePath;
	private int obstacleID;
	protected int width;
	protected int height;
	protected boolean isStiff = false;
	protected boolean isRight = true;
	private double angle;
	private boolean infiniteVoid=false;
			

	
	

	public boolean isInfiniteVoid() {
		return infiniteVoid;
	}

	public void setInfiniteVoid(boolean infiniteVoid) {
		this.infiniteVoid = infiniteVoid;
	}

	public int getObstacleID() {
		return obstacleID;
	}

	public void setObstacleID(int obstacleID) {
		this.obstacleID = obstacleID;
	}

	public String type;
	
	public Obstacle(String type) {
		this.type = type;
		location = new Location(0, 0);
		
	}
	
	

	public Location getLocation() {
		return location;
	}

	public void setLocation(double xPos,double yPos) {
		this.location.setLocation(xPos, yPos);
	}
	
	public double getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed() {
		if(this.isRight) {
			this.xSpeed ++ ;
		}
		if(this.xSpeed == 10) {
			this.isRight = false;
		}
		if(!this.isRight) {
			this.xSpeed --;
		}
		if(this.xSpeed == -10) {
			this.isRight = true;
		}
	}

	public double getySpeed() {
		return ySpeed;
	}

	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public boolean isStiff() {
		return isStiff;
	}

	public void setStiff(boolean isStiff) {
		this.isStiff = isStiff;
	}
	
	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}