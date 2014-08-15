package ib.fatninja.base.map.forest0;

import android.graphics.Point;
import ib.fatninja.R;
import ib.fatninja.base.AMovableSpriteObject.eMovement;
import ib.fatninja.base.acive.NPC.Enemy.Bear;
import ib.fatninja.base.acive.NPC.Enemy.Troll;
import ib.fatninja.base.acive.NPC.Enemy.Woolf;
import ib.fatninja.base.acive.NPC.Enemy.Builder.EnemySpawnBuilder;
import ib.fatninja.base.acive.NPC.Enemy.Builder.EnemySpawnBuilderBorder;
import ib.fatninja.base.acive.NPC.Enemy.Builder.EnemySpawnBuilderRandom;
import ib.fatninja.base.acive.NPC.Enemy.Builder.EnemySpawnInitializer;
import ib.fatninja.base.map.MapBase;

public class Map0_0 extends MapBase {
		
	@Override
	protected void initObjects() {
		for(int i = 0; i< 10; i++)
			addEvil(new EnemySpawnInitializer(new Woolf(), new Point(-1,1),eMovement.RIGHT, 0));

		for(int i = 0; i< 10; i++)
	        addEvil(new EnemySpawnInitializer(new Bear(), new Point(-5,5),eMovement.RIGHT, 0));
	
		for(int i = 0; i< 10; i++)
	        addEvil(new EnemySpawnInitializer(new Troll(), new Point(-2,8),eMovement.RIGHT, 0));		
	}

	@Override
	protected int getMapId() {
		return R.raw.level1;
	}

	@Override
	protected EnemySpawnBuilder getEnemySpawnBuilder() {
//		return EnemySpawnBuilderBorder.getInstance();
		return EnemySpawnBuilderRandom.getInstance();
	}
	
}
