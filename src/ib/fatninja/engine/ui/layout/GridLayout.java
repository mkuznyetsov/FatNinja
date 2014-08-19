package ib.fatninja.engine.ui.layout;

import android.graphics.PointF;
import ib.fatninja.engine.draw.BaseDrawableObj;
import ib.fatninja.managers.CoordinateManager;

public class GridLayout {

	private int rows;
	private int columns;
	
	private int height;
	private int width;
	
	public GridLayout(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		
		this.height = CoordinateManager.Instance().getScreenHeight() / rows;
		this.width = CoordinateManager.Instance().getScreenWidth() / columns;
	}
	
	public void setPosition(int row, int column, BaseDrawableObj obj){
		float x = column * width;
		float y = row * height;
		obj.setX(x);
		obj.setY(y);
	}	
	
	public PointF getPosition(int row, int column){
		float x = column * width;
		float y = row * height;
		return new PointF(x, y);
	}
	
}
