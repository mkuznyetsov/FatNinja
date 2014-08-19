package ib.fatninja.base.weapon;

import ib.fatninja.engine.draw.MovableSpriteObject;
import android.graphics.Canvas;


public abstract class BaseWeapon {
	protected MovableSpriteObject theOwner;
	protected int Damage = 0;
	
	public BaseWeapon(MovableSpriteObject theOwner){
		this.theOwner = theOwner;
	}
	
	public void doAction(){		
		
	}
	
	public void onActionDraw(Canvas c){
		
	}
	
	public int getDamage(){
		return this.Damage;
	}
	
	protected void setDamage(int damage){
		this.Damage = damage;
	}
	
}
