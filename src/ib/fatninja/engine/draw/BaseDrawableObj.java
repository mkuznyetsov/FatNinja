package ib.fatninja.engine.draw;

import android.graphics.Canvas;

public abstract class BaseDrawableObj implements IDrawable{
	
	protected float x = 0;
	protected float y = 0;
	protected float width = 0;
	protected float height = 0;
		
	public abstract void onDrawObj(Canvas c);

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public float getHeight(){
		return height;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getRBPointX(){
		return getX() + getWidth();
	}
	
	public float getRBPointY(){
		return getY() + getHeight();
	}
	
	public void setHeigth(float height){
		this.height = height;
	}
	
	public void setWidth(float width){
		this.width = width;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}

	public void beforeDrawObj(){}
	
	public void afterDrawObj(){}
}
