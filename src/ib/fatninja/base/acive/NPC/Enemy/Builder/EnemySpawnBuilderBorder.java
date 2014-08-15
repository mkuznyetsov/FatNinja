package ib.fatninja.base.acive.NPC.Enemy.Builder;

public class EnemySpawnBuilderBorder extends EnemySpawnBuilder{

	private static EnemySpawnBuilderBorder instance;
	
	public static EnemySpawnBuilderBorder getInstance(){
		if(instance == null)
			instance = new EnemySpawnBuilderBorder();
		return instance;
	}

	@Override
	public void build(EnemySpawnInitializer init) {
		init.getEnemy().setMovement(init.getMovement());
		init.getEnemy().setX(init.getRealPoint().x);
		init.getEnemy().setY(init.getRealPoint().y);
	}
	
}
