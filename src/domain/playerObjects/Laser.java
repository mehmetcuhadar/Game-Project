package domain.playerObjects;

import domain.game.Location;
import domain.game.NeedForSpearGame;

public class Laser {
	private Location location;
	
	public Laser(boolean isLeft) {
		if(isLeft) {
			this.location = new Location(NeedForSpearGame.getInstance().getXnoblePhantasm(), NeedForSpearGame.getInstance().getYnoblePhantasm());
		}else {
			this.location = new Location(NeedForSpearGame.getInstance().getXnoblePhantasm()+NeedForSpearGame.getInstance().getNoblePhantasmWidth(), NeedForSpearGame.getInstance().getYnoblePhantasm());

		}
		
		
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(double x, double y) {
		this.location = new Location(x, y);
	}
	
	

}
