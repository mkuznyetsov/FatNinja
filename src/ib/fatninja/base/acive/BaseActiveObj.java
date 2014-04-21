package ib.fatninja.base.acive;

import ib.fatninja.base.AMovableSpriteObject;
import ib.fatninja.base.interactObjects.Armor.Armor;
import ib.fatninja.managers.CoordinateManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/*ARMOR LIST INDEXES
 * 0 HEAD
 * 1 SHOULDER
 * 2 CHEST
 * 3 HANDS
 * 4 BRACER
 * 5 PANTS
 * 6 BOTS
 * */

public abstract class BaseActiveObj extends AMovableSpriteObject{

	protected int randomTurnRate = 0;
	protected int currentFrame = 0;
	protected eObjectType objectType = eObjectType.NONE; 
	protected int Health = 100;
	protected int TotalArmor = 0;
	protected Armor[] ArmorList;
	protected final int mapWidth = CoordinateManager.Instance().getScreenWidth();
	protected final int mapHeight = CoordinateManager.Instance().getScreenHeight();

	public BaseActiveObj (Bitmap bmp) {
		this(bmp,4,4);
	}
	
	public BaseActiveObj (Bitmap bmp, int bmpCols, int bmpRows) {
		super(bmp, bmpCols, bmpRows);
		setStep(CoordinateManager.Instance().getSpriteStep());
	}	
	
	protected void randomMove(){
		double rnd = Math.random() * 100;
		int rndPercent = (int)rnd;
		if(rndPercent <= randomTurnRate)
		{
			double rndMove = Math.random() * 4;
			int rndMoveInt = (int)rndMove;
			if(rndMoveInt == 0)
				movement = eMovement.DOWN;
			if(rndMoveInt == 1)
				movement = eMovement.UP;
			if(rndMoveInt == 2)
				movement = eMovement.LEFT;
			if(rndMoveInt == 3)
				movement = eMovement.RIGHT;
		}
	}
	
	protected void move (){
		switch(movement){
		case RIGHT:
			move_right();
			break;
		case LEFT:
			move_left();
			break;
		case UP:
			move_up();
			break;
		case DOWN:
			move_down();
			break;
		default:
			break; 
		}		
		nextFrame();
	}
	
	protected void moveReverse(){
		switch(movement){
		case DOWN:
			movement = eMovement.UP;
			move_up();
			break;
		case UP:
			movement = eMovement.DOWN;
			move_down();
			break;
		case RIGHT:
			movement = eMovement.LEFT;
			move_left();
			break;
		case LEFT:
			movement = eMovement.RIGHT;
			move_right();
			break;
		default:
			break;
		}
	}
	
	protected void checkEndMap(Canvas c){
		if(x >= mapWidth - frameWidth)
			movement = eMovement.LEFT;		
		if(x <= -1)
			movement = eMovement.RIGHT;
		if(y > c.getHeight() - frameHeight )
			movement = eMovement.UP;
		if(y <= -1)
			movement = eMovement.DOWN;
	}
	
	public void setStandardTicks(){
		
	}
	
	protected void move_left() {
		x -= step;
	}

	protected void move_right() {
		x += step;
	}

	protected void move_up() {
		y -= step;
	}

	protected void move_down() {
		y += step;
	}
	
	protected void nextFrame(){
		currentFrame++;
		if(currentFrame == bmpCols)
			currentFrame = 0;
	}
}
