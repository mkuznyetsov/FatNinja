package ib.fatninja.engine.ui;

import ib.fatninja.engine.ui.events.ITouchable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public abstract class Button extends ABaseDrawableObj implements ITouchable{

	private Paint buttonPaint = null;
	private Bitmap buttonBitmap = null;
	protected RectF position = null;
	
	public Button( float x, float y, float width, float height){
		this(x, y, width, height, null, null);
	}
	
	public Button( float x, float y, float width, float height, Bitmap bmp){
		this( x, y, width, height, bmp, null);
	}
	
	public Button( float x, float y, float width, float height, Bitmap bmp, Paint paint){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buttonBitmap = bmp;
		this.buttonPaint = paint;
		position = new RectF(x, y, x + width, y + height);
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
		c.drawBitmap(buttonBitmap, null, position, buttonPaint);
	}
}
