package ib.fatninja.engine.draw;

import android.graphics.Canvas;

public interface IDrawable {
	
	/**
	 * Calls before onDrawObj()
	 * */
	void beforeDrawObj();
	
	/**
	 * Calls from infinity loop thread
	 * @param c - canvas to draw on
	 */
	void onDrawObj(Canvas c);
	
	/**
	 * Calls after onDrawObj()
	 * */
	void afterDrawObj();
	
	/**
	 * @return X position of left top point
	 */
	float getX();
	
	/**
	 * @return Y position of left top point
	 */
	float getY();
	
	/**
	 * @return Width of an object
	 */
	float getWidth();
	/**
	 * @return Height of an object
	 */
	float getHeight();
	
	/**
	 * @return X position of right bottom point
	 */
	float getRBPointX();
	
	/**
	 * @return Y position of right bottom point
	 */
	float getRBPointY();
}
