package ib.fatninja.base.acive.NPC.Enemy;

import ib.fatninja.managers.ResourceManager;

public class Woolf extends BaseEnemy{

	public Woolf() {
		super(ResourceManager.Instance().getWoolfRes());
		setStandardTicks();
	}

	@Override
	public void setStandardTicks(){
		setTicks(1);
	}

	@Override
	protected int getTurnRate() {
		return 3;
	}
}
