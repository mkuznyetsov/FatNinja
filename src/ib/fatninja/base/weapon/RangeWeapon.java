package ib.fatninja.base.weapon;

import ib.fatninja.base.AMovableSpriteObject;
import ib.fatninja.base.weapon.unit.AAmmo;

public class RangeWeapon extends AWeapon{

	protected int ReloadingInTicks = 5;
	protected int Damage = 10;
	
	public AAmmo[] AmmoList = null;
	
	public RangeWeapon(AMovableSpriteObject theOwner) {
		super(theOwner);
		
	}
	
	protected void setAmmoCapacity(int ammoCapacity){
		AmmoList = new AAmmo[ammoCapacity];
	}
	
	public int getAmmoCapacity(){
		return AmmoList == null ? 0 : AmmoList.length;
	}
	
	public int getReloadingTicks(){
		return ReloadingInTicks;
	}
	
}
