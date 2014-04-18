package ib.fatninja.engine.ui;

import android.graphics.Canvas;

public interface IDrawable {
	void onDrawObj(Canvas c);
	float getX();
	float getY();
	float getWidth();
	float getHeight();
	float getRBPointX();
	float getRBPointY();
}
