package ib.fatninja.ui.menu;

import ib.fatninja.engine.ui.events.ITouchable;

import java.util.ArrayList;
import java.util.List;

public class MenuTouchHandler {
	
	private List<ITouchable> touchableElements = new ArrayList<ITouchable>();
	private static MenuTouchHandler instance;
	
	public static MenuTouchHandler Instance(){
		if(instance == null)
			instance = new MenuTouchHandler();
		return instance;
	}
	
	public void clear(){
		touchableElements = new ArrayList<ITouchable>();	
	}
	
	public void addElement(ITouchable element){
		touchableElements.add(element);
	}
	
	public void touch(float x, float y){
		HandleTouch(x, y);
	}
	
	private void HandleTouch(float touchX, float touchY){	
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
				selectedElement.onTouchClick(touchX, touchY);
			}
			catch(Exception e){
				
			}
		}
	}
}