package ib.fatninja.engine.collision;

import java.util.ArrayList;
import java.util.List;

import ib.fatninja.base.acive.Player.FatNinja;
import ib.fatninja.managers.CoordinateManager;

public class CollisionHandler {
	
	private static final float OVERSIGHT = CoordinateManager.Instance().getCollisionError();
	private static List<ICollisionable> elements = new ArrayList<ICollisionable>();
	
	public static void clear(){
		elements.clear();
		elements.add(FatNinja.Instance());
	}
	
	public static void add(ICollisionable element){
		elements.add(element);
	}
	
	public static void findCollision(){
		for(int i = 0; i< elements.size(); i++){
			ICollisionable obj1 = elements.get(i);
			for(int j = 0; j < elements.size();j++ ){
				ICollisionable obj2 = elements.get(j);
				if(obj1.equals(obj2))
					continue;
				if((obj1.getX() + OVERSIGHT >= obj2.getX() + OVERSIGHT 
					&& obj1.getX() + OVERSIGHT <= (obj2.getRBPointX() - OVERSIGHT)
					|| (obj1.getRBPointX() - OVERSIGHT) >= obj2.getX() + OVERSIGHT
						&& (obj1.getRBPointX() - OVERSIGHT) <= (obj2.getRBPointX())- OVERSIGHT)
				&& (obj1.getY() + OVERSIGHT >= obj2.getY() + OVERSIGHT 
					&& obj1.getY() + OVERSIGHT <= (obj2.getRBPointY() - OVERSIGHT)
					|| (obj1.getRBPointY() - OVERSIGHT) >= obj2.getY() + OVERSIGHT
						&& (obj1.getRBPointY() - OVERSIGHT) <= (obj2.getRBPointY() - OVERSIGHT)))
				{
					obj1.onCollision(obj2);
					obj2.onCollision(obj1);
				}
			}
		}
	}
}
