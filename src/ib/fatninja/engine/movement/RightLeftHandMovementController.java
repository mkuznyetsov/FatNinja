package ib.fatninja.engine.movement;

import ib.fatninja.engine.draw.MovableSpriteObject.eMovement;
import ib.fatninja.managers.CoordinateManager;
import android.graphics.Canvas;

public class RightLeftHandMovementController implements IMovementController{

	private eMovement movement;
	private float centerOfX;
	
	public RightLeftHandMovementController(){
		centerOfX = getWidth() / 2;
		setDefault();
	}
	
	@Override
	public void onTouchClick(float x, float y) {
		if (centerOfX <= x){
			switch (movement) {
			case RIGHT:
				movement = eMovement.DOWN;
				break;
			case DOWN:
				movement = eMovement.LEFT;
				break;				
			case LEFT:
				movement = eMovement.UP;
				break;
			case UP:
				movement = eMovement.RIGHT;
				break;
			default:
				break;
			}				
		}
		else{
			switch (movement) {
			case RIGHT:
				movement = eMovement.UP;
				break;
			case DOWN:
				movement = eMovement.RIGHT;
				break;				
			case LEFT:
				movement = eMovement.DOWN;
				break;
			case UP:
				movement = eMovement.LEFT;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void onTouchRelease(float x, float y) {
	}

	@Override
	public void beforeDrawObj() {}

	@Override
	public void onDrawObj(Canvas c) {}

	@Override
	public void afterDrawObj() {}

	@Override
	public float getX() {
		return 0;
	}

	@Override
	public float getY() {
		return 0;
	}

	@Override
	public float getWidth() {
		return CoordinateManager.Instance().getScreenWidth();
	}

	@Override
	public float getHeight() {
		return CoordinateManager.Instance().getScreenHeight();
	}

	@Override
	public float getRBPointX() {
		return getX() + getWidth();
	}

	@Override
	public float getRBPointY() {
		return getY() + getHeight();
	}

	@Override
	public eMovement getMovement() {
		return movement;
	}

	@Override
	public void setDefault() {		
		movement = eMovement.NONE;
	}
	
	@Override
	public void setMovement(eMovement movement) {
		this.movement = movement;		
	}

}
