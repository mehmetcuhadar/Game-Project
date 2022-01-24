package domain.Ymir;

import java.util.Random;



public class Ymir {
	private static Ymir instance;

	private Ymir() {

	}

	public static Ymir getInstance() {
		if (instance == null)
			instance = new Ymir();
		return instance;
	}

	public String generateRandomAbilities() {
		Random rand = new Random();
		
		String[] myOptions = { "hollowPurple", "doubleAccel", "infiniteVoid" };
		double random = rand.nextDouble() * 100;
		if (random >= 50.0) {
			int index = rand.nextInt(3);
			String ability = myOptions[index];
			return ability;

		}
		return null;

	}
	

}
