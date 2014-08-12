package ib.fatninja.base.terra;

import ib.fatninja.base.ASpriteObject;
import ib.fatninja.engine.collision.ICollisionable;
import ib.fatninja.managers.ResourceManager;
import android.graphics.Canvas;

public class Grass extends ASpriteObject {
		
	public Grass( int x, int y){	
		super(ResourceManager.Instance().getGrassRes());
		setX(x);
		setY(y);
	}
	
	public void onCollision(ICollisionable handledObj) {
	}

	public eObjectType getObjectType() {
		return eObjectType.BACKGROUND;
	}

	public void onDrawObj(Canvas c) {
		c.drawBitmap(bmp, x, y, null);	
	}
}
