package ib.fatninja.base.acive.Player;

import ib.fatninja.base.acive.BaseActiveObj;
import ib.fatninja.base.weapon.AWeapon;
import ib.fatninja.base.weapon.type.Blaster;
import ib.fatninja.engine.collision.CollisionHandler;
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
	
	private static FatNinja instance;
	
	public static FatNinja Instance(){
		if(instance == null)
			instance = new FatNinja();
		return instance;
	}
	
	private FatNinja() {
		super(ResourceManager.Instance().getFatNinjaRes());
		clear();
		selectedWeapon = new Blaster(this);
		objectType = eObjectType.PLAYER;
	}
	
	public void clear(){
		setStandardTicks();
		setY(200);
		setX(0);
		isDead = false;
		setMovement(eMovement.RIGHT);		
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
			move();
			checkEndMap(c);	
		}	
		c.drawBitmap(bitmapList.get(movement).get(currentFrame), x, y, null);
	}

	public void setMovement(eMovement movement){
		this.movement = movement;
	}

	public void onCollision(ICollisionable handledObj) {
		switch(handledObj.getObjectType()){
			case BLOCK:
				moveReverse();
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

//		if (distance_x > distance_y) {
			if (x <= click_x){
				switch (movement) {
				case RIGHT:
					movement = eMovement.DOWN;
					break;
				case DOWN:
					movement = eMovement.LEFT;
					break;				
				case LEFT:
					movement = eMovement.UP;
					break;
				case UP:
					movement = eMovement.RIGHT;
					break;
				default:
					break;
				}				
			}
			else{
				switch (movement) {
				case RIGHT:
					movement = eMovement.UP;
					break;
				case DOWN:
					movement = eMovement.RIGHT;
					break;				
				case LEFT:
					movement = eMovement.DOWN;
					break;
				case UP:
					movement = eMovement.LEFT;
					break;
				default:
					break;
				}
			}
//		} 
//		else {
//			if (y >= click_y)
//				movement = eMovement.UP;
//			else
//				movement = eMovement.DOWN;
//		}
	}

	public void onTouchClick(float x, float y) {
	}
	
	public void onTouchRelease(float x, float y) {;
	}

	public eObjectType getObjectType() {
		return objectType;
	}
}