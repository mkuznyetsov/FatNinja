package ib.fatninja.engine.ui;

import ib.fatninja.ui.menu.MenuTouchHandler;
import android.graphics.Bitmap;
import android.graphics.Paint;

public class MenuButton extends Button{

	public MenuButton( int x, int y, int width, int height){
		this(x, y, width, height, null, null);
	}
	
	public MenuButton(int x, int y, int width, int height, Bitmap bmp){
		this(x, y, width, height, bmp, null);
	}	
	
	public MenuButton(float x, float y, float width, float height, Bitmap bmp, Paint paint) {
		super(x, y, width, height, bmp, paint);
		MenuTouchHandler.Instance().addElement(this);
	}
}
