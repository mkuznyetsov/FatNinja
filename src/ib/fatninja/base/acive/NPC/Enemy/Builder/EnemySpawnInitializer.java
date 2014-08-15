package ib.fatninja.base.acive.NPC.Enemy.Builder;

import android.graphics.Point;
import ib.fatninja.base.AMovableSpriteObject.eMovement;
import ib.fatninja.managers.CoordinateManager;

public class EnemySpawnInitializer {

	private Point abstractPoint;
	private Point realPoint;
	private eMovement movement;
	private int spawnDelay;
	
	public EnemySpawnInitializer(Point abstractPoint, eMovement movement, int spawnDelay){
		this.abstractPoint = abstractPoint; 
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
	
}
