package domain.magicalAbilities;

import domain.game.Location;

public class Ability {
	private Location location;
	private int abilityID;
	private int width = 20;
	private int height = 20;
	public String type;
	
	public Ability(String type,int x,int y) {
		this.type = type;
		location = new Location(x, y);
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(double xPos,double yPos) {
		this.location.setLocation(xPos, yPos);
	}
	public int getAbilityID() {
		return abilityID;
	}
	public void setAbilityID(int abilityID) {
		this.abilityID = abilityID;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	
	

}
