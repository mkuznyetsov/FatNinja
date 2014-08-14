package ib.fatninja.base.acive.NPC.Enemy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import ib.fatninja.base.acive.BaseActiveObj;

public abstract class BaseEnemy extends BaseActiveObj{

	public BaseEnemy(Bitmap bmp) {
		super(bmp);
	}

	public BaseEnemy (Bitmap bmp, int bmpCols, int bmpRows) {
		super(bmp, bmpCols, bmpRows);
	}
	
	public void onDrawObj(Canvas c) {
		if(waitDelay()){
			randomMove();
		}
		checkEndOfMap(c);		
		c.drawBitmap(bitmapList.get(movement).get(currentFrame), x, y, null);
	}

	public eObjectType getObjectType() {
		return eObjectType.ENEMY;
	}
}
