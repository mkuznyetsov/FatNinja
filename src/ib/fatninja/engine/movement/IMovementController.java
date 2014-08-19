package ib.fatninja.engine.movement;

import ib.fatninja.engine.draw.IDrawable;
import ib.fatninja.engine.draw.MovableSpriteObject.eMovement;
import ib.fatninja.engine.ui.events.ITouchable;

public interface IMovementController extends ITouchable, IDrawable{

	/**
	 * Return the movement of player
	 * */
	eMovement getMovement();
	
	/**
	 * Set movement and variables to default
	 * */
	void setDefault();
}
