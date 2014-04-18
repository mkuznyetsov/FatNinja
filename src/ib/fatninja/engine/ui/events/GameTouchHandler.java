package ib.fatninja.engine.ui.events;

import java.util.ArrayList;
import java.util.List;

public class GameTouchHandler {
	private static List<ITouchable> touchableElements = new ArrayList<ITouchable>();
	
	public static void addElement(ITouchable element){
		touchableElements.add(element);
	}

	public static void clearList(){
		touchableElements = new ArrayList<ITouchable>();
	}
	
	public static void touch(float x, float y){
		HandleTouch(x, y, false);
	}
	
	public static void touchRelease(float x, float y){
		HandleTouch(x, y, true);
	}
	
	private static void HandleTouch(float touchX, float touchY
			, boolean isRelease){	
		ITouchable el;
		ITouchable selectedElement = null;
		for(int i =0;i < touchableElements.size();i++){
			el = touchableElements.get(i);
			if(touchX >= el.getX() && touchX <= el.getX() + el.getWidth() && touchY >= el.getY() && touchY <= el.getY() + el.getHeight()){
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
				
			}
		}
	}
}
