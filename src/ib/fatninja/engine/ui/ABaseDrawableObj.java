package ib.fatninja.engine.ui;

import android.graphics.Canvas;

public abstract class ABaseDrawableObj implements IDrawable{
	
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
	
	protected void setHeigth(float height){
		this.height = height;
	}
	
	protected void setWidth(float width){
		this.width = width;
	}
	
	protected void setX(float x){
		this.x = x;
	}
	
	protected void setY(float y){
		this.y = y;
	}

	public void beforeDrawObj(){}
	
	public void afterDrawObj(){}
}
