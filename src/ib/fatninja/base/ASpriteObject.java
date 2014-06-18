package ib.fatninja.base;

import ib.fatninja.engine.collision.ICollisionable;
import ib.fatninja.engine.ui.IDrawable;
import android.graphics.Bitmap;

public abstract class ASpriteObject implements IDrawable, ICollisionable{

	public enum eObjectType{ PLAYER
		, ENEMY, SLOW, BACKGROUND, BLOCK
		, INTERACT, FRIENDLY, NONE }
	
	public boolean isRemoved = false;
	
	protected Bitmap bmp;
	protected int bmpCols		= 0;
	protected int bmpRows		= 0;
	protected int bmpWidth		= 0;
	protected int bmpHeight		= 0;
	protected int frameWidth  	= 0;
	protected int frameHeight 	= 0;
		
	protected float x 			= 0f;
	protected float y			= 0f;
	
	private boolean isCollisionDetected;
		
	protected ASpriteObject (){
		
	}
	
	protected ASpriteObject(Bitmap bmp){
		this(bmp,1,1);
	}
	
	protected ASpriteObject(Bitmap bmp, int bmpCols, int bmpRows){
		this.bmp = bmp;
		this.bmpCols = bmpCols;
		this.bmpRows = bmpRows;
		this.bmpWidth = bmp.getWidth();
		this.bmpHeight = bmp.getHeight();
		this.frameWidth = bmpWidth / bmpCols;
		this.frameHeight = bmpHeight / bmpRows;
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

	public float getWidth(){
		return frameWidth;
	}
	
	public float getHeight(){
		return frameHeight;
	}
	
	// RB - right bottom
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
		
	public void beforeDrawObj(){}
	
	public void afterDrawObj(){}
	
}
