package ib.fatninja.base;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import ib.fatninja.managers.CoordinateManager;
import android.graphics.Bitmap;

/**	
 * <p> BMP rules </p>
 * <p> 1 row = images for move down </p>
 * <p> 2 row = images for move left </p>
 * <p> 3 row = images for move right </p>
 * <p> 4 row = images for move up </p>
 * */
public abstract class AMovableSpriteObject extends ASpriteObject{
	
	public enum eMovement{
		LEFT, RIGHT, UP, DOWN, NONE
	}

	/**
	 * The direction in which the unit is faced.
	 * */
	protected eMovement movement = eMovement.NONE;

	/**
	 * The value which is added to unit position when the unit is moving.
	 * */
	protected float step 		= 0.0f;
	
	/**
	 * <p> Used to slow unit for amount of ticks. </p>
	 * <p> As less this value as faster the unit. </p>
	 */
	protected int ticks			= 0;
	
	private int currentTicks	= 0;
	
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

	/**
	 * @see {@link #step}.
	 * */
	public float getStep() {
		return step;
	}
	
	/**
	 * @see {@link #step}.
	 * */
	public void setStep(float speed) {
		this.step = speed;
	}

	/**
	 * @see {@link #ticks}.
	 * */
	public int getTicks() {
		return ticks;
	}
	
	/**
	 * @see {@link #ticks}.
	 * */
	public void setTicks(int ticks) {
		this.ticks = ticks;
	}

	/**
	 * @see {@link #movement}.
	 * */
	public eMovement getMovement() {
		return movement;
	}

	/**
	 * @see {@link #movement}.
	 * */
	public void setMovement(eMovement movement) {
		this.movement = movement;
	}
}
