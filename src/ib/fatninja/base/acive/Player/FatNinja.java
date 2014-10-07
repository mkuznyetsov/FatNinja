package ib.fatninja.base.acive.Player;

import ib.fatninja.base.acive.BaseActiveObj;
import ib.fatninja.base.weapon.BaseWeapon;
import ib.fatninja.base.weapon.type.Blaster;
import ib.fatninja.engine.collision.ICollisionable;
import ib.fatninja.engine.movement.IMovementController;
import ib.fatninja.engine.ui.events.ITouchable;
import ib.fatninja.managers.ResourceManager;
import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class FatNinja extends BaseActiveObj implements ITouchable{

	private BaseWeapon selectedWeapon ;
	public boolean isDead ;
	private int applesCount = 0;
	private IMovementController movementController;
	
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
		setMovement(eMovement.NONE);
	}
	
	@Override
	public void setStandardTicks(){
		setTicks(0);
	}
	
	@Override
	public void setMovement(eMovement movement){
		if(movement != getMovement())
		{
			movementController.setMovement(movement);
			super.setMovement(movement);
		}
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
			checkEndOfMap(c);
		}	
		c.drawBitmap(bitmapList.get(getMovement()).get(currentFrame), x, y, null);
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
				applesCount = 0;
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
	
	public void onTouchClick(float x, float y) {
	}
	
	public void onTouchRelease(float x, float y) {
	}

	public eObjectType getObjectType() {
		return objectType;
	}
	
	public void setMovementController(IMovementController movementController){
		this.movementController = movementController;
	}
	
	public void updateMovement()
	{
		setMovement(movementController.getMovement());
	}

	@Override
	protected int getTurnRate() {
		return 0;
	}
}