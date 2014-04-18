package ib.fatninja.base.weapon.unit;

import ib.fatninja.base.AMovableSpriteObject.eMovement;
import ib.fatninja.base.ASpriteObject.eObjectType;
import ib.fatninja.base.acive.Player.FatNinja;
import ib.fatninja.base.weapon.RangeWeapon;
import ib.fatninja.engine.collision.ICollisionable;
import ib.fatninja.engine.ui.IDrawable;
import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class AAmmo extends Paint implements IDrawable, ICollisionable{

	protected RangeWeapon theOwner;
	protected FatNinja.eMovement movement = eMovement.NONE;
	protected float x 			= 0f;
	protected float y 			= 0f;
	protected float range 		= 0f;
	protected float passedRange = 0f;
	protected float step		= 0f;
	protected int ticks 		= 0;
	private boolean isRunning = false;
	private boolean isReloaded = true;
	private boolean isCollisionDetected;
	
	protected AAmmo(){
		
	}
	
	public AAmmo (RangeWeapon theOwner){
		this.theOwner = theOwner;
	}
	
	@SuppressLint("DrawAllocation")
	public void onDrawObj(Canvas c){
		this.isRunning = ( passedRange <= range );
	}
	
	// where patron start flying
	public void doAction(float x, float y, eMovement movement){
		this.x = x;
		this.y = y;
		this.movement = movement;
		this.isRunning = true;
		this.passedRange = 0;
//		this.isReloaded = false;		
	}
	
	public void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public boolean isAlive(){
		return isRunning;
	}
	
	public boolean isReadyToFly(){
		return !isRunning && isReloaded;
	}

	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}	
	
	public void setStep(float step){
		this.step = step;
	}
	public float getStep(){
		return step;
	}
	
	public float getWidth(){
		return 0;
	}
	public float getHeight(){
		return 0;
	}
	
	public eObjectType getObjectType(){
		return eObjectType.FRIENDLY;
	}
	
	public float getRBPointX(){
		return getX() + getWidth();
	}
	
	public float getRBPointY(){
		return getY() + getHeight();
	}
	
	public boolean getIsCollisionDetected(){
		return isCollisionDetected;
	}
	
	public void setIsCollisionDetected(boolean val){
		isCollisionDetected = val;
	}
}
