package ib.fatninja.ui.menu;

import ib.fatninja.engine.draw.IDrawable;
import ib.fatninja.managers.CoordinateManager;
import ib.fatninja.managers.ResourceManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

public class Background implements IDrawable {

	private Bitmap cloudsBMP;
	private Bitmap forestBMP;
	private int cloudsX = 0;
	private int ticks = 0;
	private Bitmap[] ninjaList ;
	
	private int ninjaFrameIndex = 0;
	
	public Background(){		
		cloudsBMP =	ResourceManager.Instance().getCloudRes();
		forestBMP =	ResourceManager.Instance().getBackgroundRes();
		ninjaList = ResourceManager.Instance().getMenuNinjaAnimation();		
	}

	public void onDrawObj(Canvas c) {
		if(ticks++ == 7){
			ninjaFrameIndex++;
			if(ninjaFrameIndex == ninjaList.length)
				ninjaFrameIndex = 0;
			ticks = 0;
		}
		
		if(cloudsX < -getWidth()){
			cloudsX = 0;
		}

		cloudsX -= 1;
		
		c.drawColor(Color.WHITE);
		c.drawBitmap(cloudsBMP, cloudsX, 0, null);
		c.drawBitmap(forestBMP, 0, 0, null);
		c.drawBitmap(ninjaList[ninjaFrameIndex], getWidth()/4, getHeight()/3, null);
	}
	
	public float getX() {
		return 0;
	}

	public float getY() {
		return 0;
	}

	public float getWidth() {
		return CoordinateManager.Instance().getScreenWidth();
	}

	public float getHeight() {
		return CoordinateManager.Instance().getScreenHeight();
	}

	public float getRBPointX() {
		return getX() + getWidth();
	}

	public float getRBPointY() {
		return getY() + getHeight();
	}

	@Override
	public void beforeDrawObj() {	}

	@Override
	public void afterDrawObj() {	}

}
