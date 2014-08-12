package ib.fatninja.base.acive.NPC.Enemy;

import ib.fatninja.engine.collision.ICollisionable;
import ib.fatninja.managers.ResourceManager;

public class Troll extends BaseEnemy{
	
	public Troll() {
		super(ResourceManager.Instance().getTrollRes());
		setStandardTicks();
	}

	@Override
	public void onCollision(ICollisionable handledObj) {
		switch(handledObj.getObjectType()){
		case BLOCK:
			moveReverse();
			break;
		case SLOW:
			setTicks(2);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void setStandardTicks(){
		setTicks(2);
	}

	@Override
	protected int getTurnRate() {
		return 5;
	}
}
