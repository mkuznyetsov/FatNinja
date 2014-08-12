package ib.fatninja.ui;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import ib.fatninja.engine.ui.events.ITouchable;

public class TouchHandler {

	private List<ITouchable> touchableElements = new ArrayList<ITouchable>();
	
	public void clear(){
		touchableElements.clear();	
	}
	
	public void addElement(ITouchable element){
		touchableElements.add(element);
	}
	
	public void touch(float x, float y){
		handleTouch(x, y, false);
	}
	
	public void touchRelease(float x, float y){
		handleTouch(x, y, true);
	}
		
	private void handleTouch(float touchX, float touchY
			, boolean isRelease){	
		ITouchable el;
		ITouchable selectedElement = null;
		for(int i =0;i < touchableElements.size();i++){
			el = touchableElements.get(i);
			if(touchX >= el.getX() && touchX <= el.getX() + el.getWidth() 
					&& touchY >= el.getY() && touchY <= el.getY() + el.getHeight()){
				if(selectedElement == null)
					selectedElement = el;
				else if(selectedElement.getHeight() + selectedElement.getWidth() > el.getWidth() + el.getHeight())
					selectedElement = el;
			}
		}
		if(selectedElement != null){
			try{
				if(isRelease)
					selectedElement.onTouchRelease(touchX, touchY);
				else
					selectedElement.onTouchClick(touchX, touchY);
			}
			catch(Exception e){
				Log.println(Log.ERROR, "Error", "Error in touch handler:" + e.getMessage() + e.getStackTrace());
			}
		}
	}
}
