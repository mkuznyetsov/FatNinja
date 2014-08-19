package ib.fatninja.engine.collision;

import ib.fatninja.engine.draw.IDrawable;
import ib.fatninja.engine.draw.SpriteObject.eObjectType;

public interface ICollisionable extends IDrawable{

	void onCollision(ICollisionable handledObj);
	eObjectType getObjectType();
	boolean getIsCollisionDetected();
	void setIsCollisionDetected(boolean val);
}
