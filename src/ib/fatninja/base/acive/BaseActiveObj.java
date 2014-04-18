package ib.fatninja.base.acive;

import ib.fatninja.base.AMovableSpriteObject;
import ib.fatninja.base.interactObjects.Armor.Armor;
import ib.fatninja.managers.CoordinateManager;
import android.graphics.Bitmap;

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
}
