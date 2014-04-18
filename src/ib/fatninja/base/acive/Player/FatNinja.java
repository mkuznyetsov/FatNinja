package ib.fatninja.base.acive.Player;

import ib.fatninja.base.acive.BaseActiveObj;
import ib.fatninja.base.weapon.AWeapon;
import ib.fatninja.base.weapon.type.Blaster;
import ib.fatninja.engine.collision.ICollisionable;
import ib.fatninja.engine.ui.events.ITouchable;
import ib.fatninja.managers.ResourceManager;
import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class FatNinja extends BaseActiveObj implements ITouchable{

	private AWeapon selectedWeapon ;
	private double distance_x;
	private double distance_y;
	public boolean isDead ;
	private int applesCount = 0;
	
	public FatNinja() {
		super(ResourceManager.Instance().getFatNinjaRes());
		setStandardTicks();
		setY(200);
		isDead = false;
		setMovement(eMovement.RIGHT);
		selectedWeapon = new Blaster(this);
		objectType = eObjectType.PLAYER;
	}
	
	@Override
	public void setStandardTicks(){
		setTicks(0);
	}

	public void increseApples(){
		applesCount++;
	}
	
	public int getApples(){
		return applesCount;
	}
	
	@SuppressLint("DrawAllocation")
	public void onDrawObj(Canvas c) {
		selectedWeapon.onActionDraw(c);
		if(waitDelay()){
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

	public void setMovement(eMovement _movement){
		movement = _movement;
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
				setTicks(3);
				break;
			case ENEMY:
				isDead = true;
				break;
			case INTERACT:
				increseApples();
				break;
				
		default:
			break;
		}
	}

	public void weaponAction(){
		selectedWeapon.doAction();
	}
	
	public void coordinator(float click_x, float click_y) {
		
		distance_x = Math.abs(x - click_x);			
		distance_y = Math.abs(y - click_y);

		if (distance_x > distance_y) {
			if (x <= click_x)
				movement = eMovement.RIGHT;
			else
				movement = eMovement.LEFT;
		} 
		else {
			if (y >= click_y)
				movement = eMovement.UP;
			else
				movement = eMovement.DOWN;
		}
	}

	public void onTouchClick(float x, float y) {
	}
	
	public void onTouchRelease(float x, float y) {;
	}

	public eObjectType getObjectType() {
		return objectType;
	}
}