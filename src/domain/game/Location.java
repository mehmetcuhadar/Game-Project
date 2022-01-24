package domain.game;

public class Location {

	public double xLoc;
	public double yLoc;
	
	public Location(double xLoc, double yLoc) {
		this.xLoc=xLoc;
		this.yLoc=yLoc;
	}
	public void setLocation(double x, double y) {
		xLoc=x;
		yLoc=y;
	}
	public double getxLoc() {
		return xLoc;
	}
	public void setxLoc(double xLoc) {
		this.xLoc = xLoc;
	}
	public double getyLoc() {
		return yLoc;
	}
	public void setyLoc(double yLoc) {
		this.yLoc = yLoc;
	}
	
	
}
