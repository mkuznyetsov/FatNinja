package ib.fatninja.engine.collision;

import ib.fatninja.base.ASpriteObject.eObjectType;
import ib.fatninja.engine.ui.IDrawable;

public interface ICollisionable extends IDrawable{

	void onCollision(ICollisionable handledObj);
	eObjectType getObjectType();
	boolean getIsCollisionDetected();
	void setIsCollisionDetected(boolean val);
}
