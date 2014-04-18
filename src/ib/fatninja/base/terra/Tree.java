package ib.fatninja.base.terra;

import ib.fatninja.base.ASpriteObject;
import ib.fatninja.engine.collision.ICollisionable;
import ib.fatninja.managers.ResourceManager;
import android.graphics.Canvas;

public class Tree extends ASpriteObject {
		
	public Tree( int x, int y){	
		super(ResourceManager.Instance().getTreeRes());
		setX(x);
		setY(y);
	}
	
	public void onCollision(ICollisionable handledObj) {
		// TODO Auto-generated method stub
		
	}

	public eObjectType getObjectType() {
		return eObjectType.BLOCK;
	}

	public void onDrawObj(Canvas c) {
		c.drawBitmap(bmp, x,y, null);	
	}

}
