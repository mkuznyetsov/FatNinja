package ib.fatninja.engine.ui;

import ib.fatninja.ui.game.GameTouchHandler;
import android.graphics.Bitmap;
import android.graphics.Paint;

public class GameButton extends Button{

	public GameButton(int x, int y, int width, int height){
		this(x, y, width, height, null, null);
	}
	
	public GameButton(int x, int y, int width, int height, Bitmap bmp){
		this(x, y, width, height, bmp, null);
	}
	
	public GameButton(float x, float y, float width, float height, Bitmap bmp, Paint paint) {
		super(x, y, width, height, bmp, paint);
		GameTouchHandler.addElement(this);
	}
}
