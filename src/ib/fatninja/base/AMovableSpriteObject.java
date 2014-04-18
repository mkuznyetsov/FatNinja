package ib.fatninja.base;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import ib.fatninja.managers.CoordinateManager;
import android.graphics.Bitmap;

/*	BMP RULES
 * 1 ROW MOVE DOWN
 * 2 ROW MOVE LEFT
 * 3 ROW MOVE RIGHT
 * 4 ROW MOVE UP
 * */
public abstract class AMovableSpriteObject extends ASpriteObject{
	
	public enum eMovement{
		LEFT,RIGHT,UP,DOWN,NONE
	}

	protected eMovement movement = eMovement.NONE;

	protected float step 		= 0.0f;
	protected int ticks			= 0;	
	private   int currentTicks	= 0;
	
	protected Hashtable<eMovement, List<Bitmap>> bitmapList = new Hashtable<eMovement, List<Bitmap>>();
	
	protected AMovableSpriteObject (){
		
	}
	
	public AMovableSpriteObject (Bitmap bmp) {
		this(bmp,4,4);
	}
	
	public AMovableSpriteObject (Bitmap bmp, int bmpCols, int bmpRows) {
		super(bmp, bmpCols, bmpRows);
		InitBitmapList();
	}	
	
	private void InitBitmapList(){
		List<Bitmap> downList = new ArrayList<Bitmap>();
		List<Bitmap> leftList = new ArrayList<Bitmap>();
		List<Bitmap> rightList = new ArrayList<Bitmap>();
		List<Bitmap> upList = new ArrayList<Bitmap>();
		
		for(int i = 0; i< bmpCols;i++){
			Bitmap bmp_down = Bitmap.createBitmap(bmp, i*frameHeight +1, 0*frameWidth+1, frameWidth-1, frameHeight-1);
			Bitmap bmp_left = Bitmap.createBitmap(bmp, i*frameHeight +1, 1*frameWidth+1, frameWidth-1, frameHeight-1);
			Bitmap bmp_right = Bitmap.createBitmap(bmp, i*frameHeight +1, 2*frameWidth+1, frameWidth-1, frameHeight-1);
			Bitmap bmp_up = Bitmap.createBitmap(bmp, i*frameHeight +1, 3*frameWidth +1, frameWidth-1, frameHeight-1);
			
			downList.add(Bitmap.createScaledBitmap(bmp_down
					, CoordinateManager.Instance().getSpriteEdge()
					, CoordinateManager.Instance().getSpriteEdge()
					, true));
			leftList.add(Bitmap.createScaledBitmap(bmp_left
					, CoordinateManager.Instance().getSpriteEdge()
					, CoordinateManager.Instance().getSpriteEdge()
					, true));
			rightList.add(Bitmap.createScaledBitmap(bmp_right
					, CoordinateManager.Instance().getSpriteEdge()
					, CoordinateManager.Instance().getSpriteEdge()
					, true));
			upList.add(Bitmap.createScaledBitmap(bmp_up
					, CoordinateManager.Instance().getSpriteEdge()
					, CoordinateManager.Instance().getSpriteEdge()
					, true));
		}
		
		bitmapList.put(eMovement.DOWN, downList);
		bitmapList.put(eMovement.LEFT, leftList);
		bitmapList.put(eMovement.RIGHT, rightList);
		bitmapList.put(eMovement.UP, upList);		
		bitmapList.put(eMovement.NONE, downList);

		frameWidth = CoordinateManager.Instance().getSpriteEdge();
		frameHeight = CoordinateManager.Instance().getSpriteEdge();
	}	
	
	protected boolean waitDelay(){
		if( ticks == 0 )
			return true;
		boolean result = ticks == currentTicks++;
		if(result)
			currentTicks = 0;
		return result;
	}
	
	public float getStep() {
		return step;
	}
	public void setStep(float speed) {
		this.step = speed;
	}
	
	public int getTicks() {
		return ticks;
	}
	
	public void setTicks(int ticks) {
		this.ticks = ticks;
	}
	
	public eMovement getMovement() {
		return movement;
	}
	public void setMovement(eMovement movement) {
		this.movement = movement;
	}

}
