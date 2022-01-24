package domain.game;

import domain.magicalAbilities.Ability;
import domain.magicalAbilities.ChanceGiving;
import domain.magicalAbilities.MagicalHex;
import domain.magicalAbilities.NoblePhantasmExpansion;
import domain.magicalAbilities.UnstoppableEnchantedSphere;

public class AbilityFactory {
	private static AbilityFactory instance;  

	private AbilityFactory() {}  
	public static AbilityFactory getInstance() {  
		if (instance == null) {  
			instance = new AbilityFactory();  
		}  
		return instance;  
	}
	public Ability createAbility(String type,int x,int y) {
		if(type.equals("ChanceGiving"))return new ChanceGiving(x,y);
		else if(type.equals("MagicalHex"))return new MagicalHex(x,y);
		else if(type.equals("PaddleExpansion"))return   new NoblePhantasmExpansion(x,y);
		else if(type.equals("UnstoppableSphere"))return  new UnstoppableEnchantedSphere(x,y);
		else return null;

	}
	


}
