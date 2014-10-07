package ib.fatninja.base.acive.NPC.Enemy;

import ib.fatninja.managers.ResourceManager;

public class Bear extends BaseEnemy{

	public Bear() {
		super(ResourceManager.Instance().getBearRes());
		setStandardTicks();
	}
	
	@Override
	public void setStandardTicks(){
		setTicks(3);
	}

	@Override
	protected int getTurnRate() {
		return 2;
	}
}
