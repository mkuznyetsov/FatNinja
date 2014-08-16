package ib.fatninja.engine.ui;

import ib.fatninja.engine.ui.events.ITouchable;
import ib.fatninja.managers.StyleManager;
import ib.fatninja.ui.TouchHandler;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Button extends ABaseDrawableObj implements ITouchable{

	private Paint buttonPaint = null;
	private Bitmap buttonBitmap = null;
	private String text = null;
	protected RectF position = null;
	
	public Button( float x, float y, float width, float height, TouchHandler touchHandler){
		this(x, y, width, height, (Bitmap)null, null, touchHandler);
	}
	
	public Button( float x, float y, float width, float height, Bitmap bmp, TouchHandler touchHandler){
		this( x, y, width, height, bmp, null, touchHandler);
	}

    public Button( float x, float y, float width, float height, String txt, Paint paint, TouchHandler touchHandler){
		this( x, y, width, height, (Bitmap)null, paint, touchHandler);
        this.text = txt;
	}
	
	public Button( float x, float y, float width, float height, Bitmap bmp, Paint paint, TouchHandler touchHandler){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buttonBitmap = bmp;
		this.buttonPaint = paint;
		position = new RectF(x, y, x + width, y + height);
		if(touchHandler != null)
			touchHandler.addElement(this);
	}

	public void setBMP(Bitmap bmp){
		this.buttonBitmap = bmp;
	}
	
	public void onTouchClick(float x, float y) {
	}

	public void onTouchRelease(float x, float y) {
	}
	
	@Override
	public void onDrawObj(Canvas c){
		if (buttonBitmap == null) {
			c.drawText(text, x, y, buttonPaint);
		}else{
			c.drawBitmap(buttonBitmap, null, position, buttonPaint);
		}
	}
}
