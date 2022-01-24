package domain.playerObjects;

import java.awt.Image;

public class EnchantedSphere {
	
	
	private int xPos;
	private int yPos;
	
	private int width;
	private int height;
	
	private int xVelocity = 15;
	private int yVelocity = 15;
	
	private Image img;
	
	
	
	public EnchantedSphere(int x, int y, int width, int height, Image img) {
		
		this.xPos = x;
		this.yPos = y;
		
		this.width = width;
		this.height = height;
		
		this.img = img;
	}
	
	
	
	public void move() {
		
		xPos += xVelocity;
		yPos += yVelocity;
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
	
	public int getYVelocity() {
		
		return this.yVelocity;
	}
	
	public int getWidth() {
		
		return this.width;
	}
	
	public int getHeight() {
		
		return this.height;
	}
	
	public void setXVelocity(int xVel) {
		
		this.xVelocity = xVel;
	}
	
	public void setYVelocity(int yVel) {
		
		this.yVelocity = yVel;
	}
	
	public void setX(int x) {
		
		this.xPos = x;
	}
	
	public void setY(int y) {
		
		this.yPos = y;
	}



	public void setWidth(int width) {
		// TODO Auto-generated method stub
		this.width = width;
	}



	public void setHeight(int height) {
		// TODO Auto-generated method stub
		this.height = height;
	}
	

}
