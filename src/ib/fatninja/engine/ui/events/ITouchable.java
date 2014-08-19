package ib.fatninja.engine.ui.events;

import ib.fatninja.engine.draw.IDrawable;

public interface ITouchable extends IDrawable{
	
	public void onTouchClick(float x, float y);
	
	public void onTouchRelease(float x, float y);
}
