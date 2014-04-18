package ib.fatninja.base.weapon.unit;

import ib.fatninja.base.weapon.RangeWeapon;
import ib.fatninja.engine.collision.ICollisionable;
import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class BlasterAmmo extends AAmmo {

	private final float width = 20;
	private final float height = 3;
	
	private float p2X = 0;
	private float p2Y = 0;
	
	public BlasterAmmo(RangeWeapon theOwner){
		super(theOwner);
		this.setStyle(Style.FILL);
		this.setARGB(0, 47, 250, 65);
		this.setARGB(180,200, 85, 65);
		this.step = width/2;
		this.range = 600;
	}

	@SuppressLint("DrawAllocation")
	@Override
	public void onDrawObj(Canvas c ){
		super.onDrawObj(c);
		switch (movement) {
			case RIGHT:
				x += step;
				p2X = x + width;
				p2Y = y + height;
				break;
			case LEFT:
				x -= step;
				p2X = x + width;
				p2Y = y + height;
				break;
			case UP:
				y -= step;
				p2X = x + height;
				p2Y = y + width;
				break;
			case DOWN:
				y += step;
				p2X = x + height;
				p2Y = y + width;
				break;
			default:
				return;
		}
		passedRange += width;
		c.drawRect(x, y, p2X, p2Y, this);
	}

	public void onCollision(ICollisionable handledObj) {
		// TODO Auto-generated method stub
		
	}
	
}
