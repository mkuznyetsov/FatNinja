package ib.fatninja.base.terra;

import ib.fatninja.engine.collision.ICollisionable;
import ib.fatninja.engine.draw.SpriteObject;
import ib.fatninja.managers.ResourceManager;
import android.graphics.Canvas;

public class Bush extends SpriteObject {
		
	public Bush( int x, int y){	
		super(ResourceManager.Instance().getBushRes());
		setX(x);
		setY(y);
	}
		
	public void onCollision(ICollisionable handledObj) {
		// TODO Auto-generated method stub
		
	}

	public eObjectType getObjectType() {
		return eObjectType.SLOW;
	}

	public void onDrawObj(Canvas c) {
		c.drawBitmap(bmp, x, y, null);	
	}
}
