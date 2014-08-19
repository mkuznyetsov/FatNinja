package ib.fatninja.base.terra;

import ib.fatninja.base.acive.NPC.Enemy.Bear;
import ib.fatninja.engine.collision.ICollisionable;
import ib.fatninja.engine.draw.SpriteObject;
import ib.fatninja.managers.ResourceManager;
import ib.fatninja.managers.SoundManager;
import android.graphics.Canvas;

public class Apple extends SpriteObject {
	
	private boolean isFreeToPlace;
		
	public Apple( int x, int y){	
		super(ResourceManager.Instance().getAppleRes());
		isFreeToPlace = true;
		setX(x);
		setY(y);
	}
	
	public void onCollision(ICollisionable handledObj) {
		switch(handledObj.getObjectType()){
			case PLAYER:
				hideApple();
				break;
			case ENEMY:
				if(handledObj instanceof Bear)
					hideApple();
				break;
			case BACKGROUND:
				break;
			case BLOCK:
				break;
			case FRIENDLY:
				break;
			case INTERACT:
				break;
			case NONE:
				break;
			case SLOW:
				break;
			default:
				break;
		}
		
	}

	public eObjectType getObjectType() {
		return eObjectType.INTERACT;
	}

	public void onDrawObj(Canvas c) {
		c.drawBitmap(bmp, x, y, null);	
	}
	
	public boolean isFreeToPlace() {
		return isFreeToPlace;
	}

	public void setFreeToPlace(boolean isFreeToPlace) {
		this.isFreeToPlace = isFreeToPlace;
	}
	
	private void hideApple(){
		isFreeToPlace = true;
		x = -100;
		y = -100;
		SoundManager.Instance().playBitApple();
	}
}
