package ib.fatninja.base.acive.NPC.Enemy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import ib.fatninja.base.acive.BaseActiveObj;
import ib.fatninja.engine.collision.ICollisionable;

public abstract class BaseEnemy extends BaseActiveObj{

	public BaseEnemy(Bitmap bmp) {
		super(bmp);
	}

	public BaseEnemy (Bitmap bmp, int bmpCols, int bmpRows) {
		super(bmp, bmpCols, bmpRows);
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
	public void onDrawObj(Canvas c) {
		if(waitDelay()){
			randomMove();
		}
		checkEndOfMap(c);		
		c.drawBitmap(bitmapList.get(getMovement()).get(currentFrame), x, y, null);
	}

	@Override
	public eObjectType getObjectType() {
		return eObjectType.ENEMY;
	}
	
}
