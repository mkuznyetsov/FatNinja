package ib.fatninja.base.acive.NPC.Enemy.Builder;

import android.graphics.Point;
import ib.fatninja.base.acive.NPC.Enemy.BaseEnemy;
import ib.fatninja.engine.draw.MovableSpriteObject.eMovement;
import ib.fatninja.managers.CoordinateManager;

public class EnemySpawnInitializer {

	private Point abstractPoint;
	private Point realPoint;
	private eMovement movement;
	private int spawnDelay;
	private BaseEnemy enemy;
	
	public EnemySpawnInitializer(BaseEnemy enemy){
		this(enemy, null, null, 0);
	}
	
	public EnemySpawnInitializer( 
			BaseEnemy enemy
			, Point abstractPoint
			, eMovement movement
			, int spawnDelay){
		
		this.enemy = enemy;
		this.abstractPoint = abstractPoint; 
		if(abstractPoint != null)
			this.realPoint = new Point(
				CoordinateManager.Instance().getTileEdge()* abstractPoint.x
				, CoordinateManager.Instance().getTileEdge()* abstractPoint.y);
		this.movement = movement;
		this.spawnDelay = spawnDelay;
	} 
	
	public Point getAbstractPoint(){
		return abstractPoint;
	}
	
	public Point getRealPoint(){
		return realPoint;
	}
	
	public eMovement getMovement(){
		return movement;
	}
	
	public int getSpawnDelay(){
		return spawnDelay;
	}
	
	public BaseEnemy getEnemy(){
		return enemy;
	}
	
}
