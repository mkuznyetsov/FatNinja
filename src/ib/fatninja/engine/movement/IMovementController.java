package ib.fatninja.engine.movement;

import ib.fatninja.engine.draw.MovableSpriteObject.eMovement;
import ib.fatninja.engine.ui.events.ITouchable;

public interface IMovementController extends ITouchable{

	/**
	 * Return the movement of player
	 * */
	eMovement getMovement();
	
	/**
	 * Set the movement and variables to default
	 * */
	void setDefault();
	
	/**
	 * Set the movement 
	 * */
	void setMovement(eMovement movement);
}
