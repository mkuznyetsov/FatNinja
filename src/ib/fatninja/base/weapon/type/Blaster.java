package ib.fatninja.base.weapon.type;

import ib.fatninja.base.AMovableSpriteObject;
import ib.fatninja.base.weapon.RangeWeapon;
import ib.fatninja.base.weapon.unit.BlasterAmmo;
import android.graphics.Canvas;

public class Blaster extends RangeWeapon{
		
	public Blaster(AMovableSpriteObject theOwner) {
		super(theOwner);
		init();
	}
	
	private void init(){
		setAmmoCapacity(10);
		setDamage(10);
		
		for(int i = 0; i< AmmoList.length; i++){
			AmmoList[i] = new BlasterAmmo(this);
		}		
	}
	
	@Override
	public void doAction(){
		for(int i = 0; i< AmmoList.length; i++){
			if(AmmoList[i].isReadyToFly()){
				AmmoList[i].doAction(theOwner.getX()+theOwner.getWidth()/2, theOwner.getY()+theOwner.getHeight()/2, theOwner.getMovement());
				break;
			}
		}
	}

	@Override
	public void onActionDraw(Canvas c){
		for(int i= 0; i< AmmoList.length;i++){			
			if(AmmoList[i].isAlive())
				AmmoList[i].onDrawObj(c);
		}
	}	
}
