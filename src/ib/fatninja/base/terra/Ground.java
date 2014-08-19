package ib.fatninja.base.terra;

import ib.fatninja.engine.collision.ICollisionable;
import ib.fatninja.engine.draw.SpriteObject;
import ib.fatninja.managers.ResourceManager;
import android.graphics.Canvas;

public class Ground extends SpriteObject{
	
	public Ground( int x, int y){	
		super(ResourceManager.Instance().getGroundRes());
		setX(x);
		setY(y);
	}
		
	public void onCollision(ICollisionable handledObj) {
		// TODO Auto-generated method stub
		
	}

	public eObjectType getObjectType() {
		return eObjectType.BACKGROUND;
	}

	public void onDrawObj(Canvas c) {
		c.drawBitmap(bmp, x, y, null);	
	}
}
