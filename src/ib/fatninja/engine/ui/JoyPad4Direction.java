package ib.fatninja.engine.ui;

import ib.fatninja.base.AMovableSpriteObject.eMovement;
import ib.fatninja.engine.ui.events.ITouchable;
import ib.fatninja.engine.ui.events.GameTouchHandler;
import ib.fatninja.managers.ResourceManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class JoyPad4Direction extends ABaseDrawableObj implements ITouchable{
	
	private float cx = 0.0f;
	private float cy = 0.0f;
	
	private Path buttonUp;
	private Path buttonDown;
	private Path buttonLeft;
	private Path buttonRight;

	private Paint buttonPaint;
	
	/*
	 * A-----B
	 * |  O  |
	 * D-----C
	*/
	private PointF A;
	private PointF B;
	private PointF C;
	private PointF D;
	private PointF O;
	private PointF touchPoint;
	
	private eMovement movement;
	
	public JoyPad4Direction(float x, float y, float width, float height){
		movement = eMovement.NONE;
		setX(x);
		setY(y);
		setWidth(width);
		setHeigth(height);
		cx = x+width/2;
		cy = y+height/2;
		touchPoint = new PointF();
		touchPoint.set(cx, cy);
		buttonPaint = new Paint();
		buttonPaint.setStrokeWidth(2);
		buttonPaint.setColor(android.graphics.Color.RED);
		buttonPaint.setStyle(Paint.Style.STROKE);
        buttonPaint.setAntiAlias(true);

		A = new PointF(x,y);
		B = new PointF(x+width,y);
		C = new PointF(x+width,y+height);
		D = new PointF(x,y+height);
		O = new PointF(cx, cy);

        buttonUp = new Path();
        buttonUp.moveTo(A.x, A.y);
        buttonUp.lineTo(B.x, B.y);
        buttonUp.lineTo(O.x, O.y);
        buttonUp.lineTo(A.x, A.y);
        buttonUp.close();
        
        buttonDown = new Path();
        buttonDown.moveTo(C.x, C.y);
        buttonDown.lineTo(D.x, D.y);
        buttonDown.lineTo(O.x, O.y);
        buttonDown.lineTo(C.x, C.y);
        buttonDown.close();
        
        buttonLeft = new Path();
        buttonLeft.moveTo(A.x, A.y);
        buttonLeft.lineTo(D.x, D.y);
        buttonLeft.lineTo(O.x, O.y);
        buttonLeft.lineTo(A.x, A.y);
        buttonLeft.close();
        
        buttonRight = new Path();
    	buttonRight.moveTo(B.x, B.y);
        buttonRight.lineTo(C.x, C.y);
        buttonRight.lineTo(O.x, O.y);
        buttonRight.lineTo(B.x, B.y);
        buttonRight.close();
        

        buttonUp.close();
		
		GameTouchHandler.addElement(this);
	}

	public void onDrawObj(Canvas c) {		
	/*	c.drawPath(buttonUp, buttonPaint);
		c.drawPath(buttonDown, buttonPaint);
		c.drawPath(buttonLeft, buttonPaint);
		c.drawPath(buttonRight, buttonPaint);*/
		c.drawBitmap(ResourceManager.Instance().getJoyStick(), x, y, null);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	public eMovement getMovement(){
		return movement;
	}
	
	public void onTouchClick(float x, float y) {
		touchPoint.x = x;
		touchPoint.y = y;
		setMovement();
	}
	
	public void onTouchRelease(float x, float y){
	}
	
	public void setDefault(){
		movement = eMovement.NONE;
		touchPoint.x = 0.0f;
		touchPoint.y = 0.0f;
	}
	
	private void setMovement(){
		if(pointInTriangle(touchPoint,O,B,C))
			movement = eMovement.RIGHT;
		else if(pointInTriangle(touchPoint,O,A,D))
			movement = eMovement.LEFT;
		else if(pointInTriangle(touchPoint,O,A,B))
			movement = eMovement.UP;
		else if(pointInTriangle(touchPoint,O,D,C))
			movement = eMovement.DOWN;	
	}
	
	private boolean pointInTriangle(PointF pt, PointF v1, PointF v2, PointF v3)
	{
	  boolean b1, b2, b3;

	  b1 = sign(pt, v1, v2) < 0.0f;
	  b2 = sign(pt, v2, v3) < 0.0f;
	  b3 = sign(pt, v3, v1) < 0.0f;

	  return ((b1 == b2) && (b2 == b3));
	}

	private float sign(PointF p1, PointF p2, PointF p3)
	{
	  return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
	}
}
