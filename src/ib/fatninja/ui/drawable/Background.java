package ib.fatninja.ui.drawable;
import ib.fatninja.engine.ui.IDrawable;
import ib.fatninja.managers.CoordinateManager;
import ib.fatninja.managers.ResourceManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

public class Background implements IDrawable {

	Bitmap cloudsBMP;
	Bitmap forestBMP;
	Bitmap ninjaBMP;
	int cloudsX = 0;
	int ticks = 0;
	Bitmap[] ninjaList ;
	
	int ninjaFrameIndex = 0;
	
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
		
		if(cloudsX < -getWidth())
		{
			cloudsX = 0;
		}

		cloudsX -= 1;
		
		c.drawColor(Color.WHITE);
		c.drawBitmap(cloudsBMP, cloudsX, 0, null);
		c.drawBitmap(forestBMP, 0, 0, null);
		c.drawBitmap(ninjaList[ninjaFrameIndex], getWidth()/3, getHeight()/2, null);
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
		return	getX() + getWidth();
	}

	public float getRBPointY() {
		return	getY() + getHeight();
	}

}
