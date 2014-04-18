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
			switch(movement){
				case RIGHT:
					move_right();
					break;
				case LEFT:
					move_left();
					break;
				case UP:
					move_up();
					break;
				case DOWN:
					move_down();
					break;
				default:
					break; 
			}
			currentFrame++;
			if(currentFrame == bmpCols)
				currentFrame = 0;
		}
		if(x >= mapWidth - frameWidth)
			movement = eMovement.LEFT;		
		if(x <= -1)
			movement = eMovement.RIGHT;
		if(y > c.getHeight() - frameHeight )
			movement = eMovement.UP;
		if(y <= -1)
			movement = eMovement.DOWN;
		
		c.drawBitmap(bitmapList.get(movement).get(currentFrame), x, y, null);
		
	}

	public void onCollision(ICollisionable handledObj) {
		switch(handledObj.getObjectType()){
		case BLOCK:
			switch(movement){
			case DOWN:
				movement = eMovement.UP;
				move_up();
				break;
			case UP:
				movement = eMovement.DOWN;
				move_down();
				break;
			case RIGHT:
				movement = eMovement.LEFT;
				move_left();
				break;
			case LEFT:
				movement = eMovement.RIGHT;
				move_right();
				break;
			default:
				break;
		}
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
