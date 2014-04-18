package ib.fatninja.base.weapon;

import ib.fatninja.base.AMovableSpriteObject;
import android.graphics.Canvas;


public abstract class AWeapon {
	protected AMovableSpriteObject theOwner;
	protected int Damage = 0;
	
	public AWeapon(AMovableSpriteObject theOwner){
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
