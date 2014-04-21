package ib.fatninja.base.acive.NPC.Enemy;

import ib.fatninja.base.acive.BaseActiveObj;
import ib.fatninja.engine.collision.ICollisionable;
import ib.fatninja.managers.ResourceManager;
import android.graphics.Canvas;

public class Bear extends BaseActiveObj{

	public Bear() {
		super(ResourceManager.Instance().getBearRes());
		setStandardTicks();
		randomTurnRate = 2;
	}
	
	@Override
	public void setStandardTicks(){
		setTicks(3);
	}

	public void onDrawObj(Canvas c) {
		if(waitDelay()){
			randomMove();			
			move();
			currentFrame++;
			if(currentFrame == bmpCols)
				currentFrame = 0;
		}
		checkEndMap(c);
		c.drawBitmap(bitmapList.get(movement).get(currentFrame), x, y, null);		
	}

	public void onCollision(ICollisionable handledObj) {
		switch(handledObj.getObjectType()){
		case BLOCK:
			moveReverse();
			break;
		case SLOW:
			setTicks(2);
			break;
		case INTERACT:
			
			break;
		default:
			break;
		}
	}

	public eObjectType getObjectType() {
		return eObjectType.ENEMY;
	}

}
