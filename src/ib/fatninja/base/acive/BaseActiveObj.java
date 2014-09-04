package ib.fatninja.base.acive;

import ib.fatninja.base.interactObjects.Armor.Armor;
import ib.fatninja.engine.draw.MovableSpriteObject;
import ib.fatninja.managers.CoordinateManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class BaseActiveObj extends MovableSpriteObject{

	protected int currentFrame = 0;
	protected eObjectType objectType = eObjectType.NONE; 
	protected int Health = 100;
	protected int TotalArmor = 0;
	protected Armor[] ArmorList;
	protected final int mapWidth = CoordinateManager.Instance().getScreenWidth();
	protected final int mapHeight = CoordinateManager.Instance().getScreenHeight();

	public BaseActiveObj (Bitmap bmp) {
		this(bmp,4,4);
	}
	
	public BaseActiveObj (Bitmap bmp, int bmpCols, int bmpRows) {
		super(bmp, bmpCols, bmpRows);
		setStep(CoordinateManager.Instance().getSpriteStep());
	}	
	
	/**
	 * Turn and move unit in random direction 
	 * according to {@link #getTurnRate} method.
	 * */
	protected void randomMove(){
		double rnd = Math.random() * 100;
		int rndPercent = (int)rnd;
		if(rndPercent <= getTurnRate())
		{
			double rndMove = Math.random() * 4;
			int rndMoveInt = (int)rndMove;
			if(rndMoveInt == 0)
				movement = eMovement.DOWN;
			if(rndMoveInt == 1)
				movement = eMovement.UP;
			if(rndMoveInt == 2)
				movement = eMovement.LEFT;
			if(rndMoveInt == 3)
				movement = eMovement.RIGHT;
		}
		move();
	}
	
	protected void move(){
		switch(movement){
		case RIGHT:
			moveRight();
			break;
		case LEFT:
			moveLeft();
			break;
		case UP:
			moveUp();
			break;
		case DOWN:
			moveDown();
			break;
		default:
			break; 
		}		
		nextFrame();
	}
	
	protected void moveReverse(){
		switch(movement){
		case DOWN:
			movement = eMovement.UP;
			break;
		case UP:
			movement = eMovement.DOWN;
			break;
		case RIGHT:
			movement = eMovement.LEFT;
			break;
		case LEFT:
			movement = eMovement.RIGHT;
			break;
		default:
			break;
		}
		move();
	}
	
	protected void checkEndOfMap(Canvas c){
		if(x >= mapWidth - frameWidth)
			movement = eMovement.LEFT;		
		if(x <= -1)
			movement = eMovement.RIGHT;
		if(y > c.getHeight() - frameHeight )
			movement = eMovement.UP;
		if(y <= -1)
			movement = eMovement.DOWN;
	}
	
	/**
	 * <p> This is default value for ticks. </p>
	 * <p> Amount of ticks will be set to this value before drawing the unit</p>
	 * @see {@link MovableSpriteObject#ticks}
	 * */
	public void setStandardTicks(){
	}
	
	protected void moveLeft() {
		x -= step;
	}

	protected void moveRight() {
		x += step;
	}

	protected void moveUp() {
		y -= step;
	}

	protected void moveDown() {
		y += step;
	}
	
	protected void nextFrame(){
		currentFrame++;
		if(currentFrame == bmpCols)
			currentFrame = 0;
	}
	
	/**
	 * <p> This value is a chance ( in percent ) to turn unit in random direction.</p>
	 * */
	protected abstract int getTurnRate();
}
