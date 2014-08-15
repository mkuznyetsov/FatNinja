package ib.fatninja.base.acive.NPC.Enemy.Builder;

import ib.fatninja.base.AMovableSpriteObject.eMovement;
import ib.fatninja.managers.CoordinateManager;

public class EnemySpawnBuilderRandom extends EnemySpawnBuilder{

	private static EnemySpawnBuilderRandom instance;
	
	public static EnemySpawnBuilderRandom getInstance(){
		if(instance == null)
			instance = new EnemySpawnBuilderRandom();
		return instance;
	}
	
	@Override
	public void build(EnemySpawnInitializer init) {
		int i = (int)(Math.random() * 10);
	    int rndMovement = (int)(Math.random() * 4);
	    int rndX = (int)(Math.random() * CoordinateManager.Instance().getScreenWidth());
	    int rndY = (int)(Math.random() * CoordinateManager.Instance().getScreenHeight());

	    init.getEnemy().setX(rndX*i);
	    init.getEnemy().setY(rndY*i);
	    init.getEnemy().setMovement(getMovement(rndMovement));		
	}	
    
	private eMovement getMovement(int number){
		switch (number)
		{
			case 0:
				return eMovement.LEFT;
			case 1:
				return eMovement.UP;
			case 2:
				return eMovement.LEFT;
			case 3:
			default:
				return eMovement.DOWN;
		}
	}
}
