package ib.fatninja.base.weapon;

import ib.fatninja.base.weapon.unit.BaseAmmo;
import ib.fatninja.engine.draw.MovableSpriteObject;

public class RangeWeapon extends BaseWeapon{

	protected int ReloadingInTicks = 5;
	protected int Damage = 10;
	
	public BaseAmmo[] AmmoList = null;
	
	public RangeWeapon(MovableSpriteObject theOwner) {
		super(theOwner);
		
	}
	
	protected void setAmmoCapacity(int ammoCapacity){
		AmmoList = new BaseAmmo[ammoCapacity];
	}
	
	public int getAmmoCapacity(){
		return AmmoList == null ? 0 : AmmoList.length;
	}
	
	public int getReloadingTicks(){
		return ReloadingInTicks;
	}
	
}
