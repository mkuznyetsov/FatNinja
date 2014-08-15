package ib.fatninja.base.map.forest0;

import ib.fatninja.R;
import ib.fatninja.base.acive.NPC.Enemy.Bear;
import ib.fatninja.base.acive.NPC.Enemy.Troll;
import ib.fatninja.base.acive.NPC.Enemy.Woolf;
import ib.fatninja.base.acive.NPC.Enemy.Builder.EnemySpawnBuilder;
import ib.fatninja.base.acive.NPC.Enemy.Builder.EnemySpawnBuilderRandom;
import ib.fatninja.base.map.MapBase;

public class Map0_0 extends MapBase{

	private EnemySpawnBuilder enemySpawnBuilder = new EnemySpawnBuilderRandom();
	
	@Override
	protected void initObjects() {        
		for(int i = 0; i< 10; i++)
			addEvil(new Woolf(), i);

		for(int i = 0; i< 10; i++)
	        addEvil(new Bear(), i);
	
		for(int i = 0; i< 10; i++)
	        addEvil(new Troll(), i);		
	}

	@Override
	protected int getMapId() {
		return R.raw.level1;
	}

	@Override
	protected EnemySpawnBuilder getEnemySpawnBuilder() {
		return enemySpawnBuilder;
	}
	
}
