package domain.playerObjects;

import java.awt.Image;

public class NoblePhantasm {
	
	private int xPos;
	private int yPos;
	
	private int width;
	private int height;
	
	private Image img;
	
	private int xVelocity;
	
	private int rotationAngle = 0;
	private boolean rotationDirection;
	
	private int[] xLocations = new int[4];
	private int[] yLocations = new int[4];

	


	public NoblePhantasm(int x, int y, int width, int height, Image img) {
		
		this.xPos = x;
		this.yPos = y;
		
		this.width = width;
		this.height = height;
		
		this.img = img;
	}

	
	public void move() {
		
		xPos += xVelocity;
	}
	
	public int getX() {
		
		return this.xPos;
	}
	
	public int getY() {
		
		return this.yPos;
	}
	
	
	public int getXVelocity() {
		
		return this.xVelocity;
	}
	
	public int getRotationAngle() {
		
		return this.rotationAngle;
	}
	
	public boolean getRotationDirection() {
		
		return this.rotationDirection;
	}
	
	public int getWidth() {
		
		return this.width;
	}
	
	public int getHeight() {
		
		return this.height;
	}
	
	public void setWidth(int width) {
		
		this.width = width;
	}
	
	public void setHeight(int height) {
		
		this.height = height;
	}
	
	public void setXVelocity(int xVel) {
		
		this.xVelocity = xVel;
	}
	
	
	public void setX(int x) {
		
		this.xPos = x;
	}
	
	public void setY(int y) {
		
		this.yPos = y;
	}


	public void setRotationAngle() {
		// TODO Auto-generated method stub
		if(getRotationDirection()==true) {
			if(getRotationAngle()==-45) {
				this.rotationAngle = -40;
			}else {
				this.rotationAngle += 20;
			}
			if(getRotationAngle()>40) this.rotationAngle = 45;
		}else{
			if(getRotationAngle()==45) {
				this.rotationAngle = 40;
			}else {
				this.rotationAngle -= 20;
			}
			if(getRotationAngle()<-40) this.rotationAngle = -45;
		}
		
		//xPos += (int) (this.width * Math.cos(this.rotationAngle));
		//yPos -= (int) (this.width * Math.sin(this.rotationAngle));
	}


	public void setRotationDirection(boolean b) {
		// TODO Auto-generated method stub
		this.rotationDirection = b;
	}


	public void setnoblePhantasmLoc(int angle) {
		// TODO Auto-generated method stub
		/*switch (angle) {
		case 20:
			setX(getX() + 10);
			setY(555);
			break;
		case 40:
			setX(getX() + 15);
			setY(530);
			break;
		case 45:
			setX(getX() + 5);
			setY(520);

			break;
		case -20:
			setX(getX() + 10);
			setY(700);
			break;
		case -40:
			setX(getX() + 15);
			setY(680);
			break;
		case -45:
			setX(getX() + 5);
			setY(650);
			break;

		default:
			break;
		}*/
		
	}


	public void setnoblePhantasmHeight(int i) {
		// TODO Auto-generated method stub
		this.height = i;
	}
	
	public int[] getxLocations() {
		return xLocations;
	}


	public void setxLocations(int[] xLocations) {
		this.xLocations = xLocations;
	}


	public int[] getyLocations() {
		return yLocations;
	}


	public void setyLocations(int[] yLocations) {
		this.yLocations = yLocations;
	}


	
}