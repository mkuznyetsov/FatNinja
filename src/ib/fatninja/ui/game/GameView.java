package ib.fatninja.ui.game;

import java.io.IOException;
import ib.fatninja.base.acive.Player.FatNinja;
import ib.fatninja.base.map.MapBase;
import ib.fatninja.base.map.forest0.Map0_0;
import ib.fatninja.base.map.forest0.Map0_1;
import ib.fatninja.base.terra.Apple;
import ib.fatninja.engine.collision.CollisionHandler;
import ib.fatninja.engine.movement.IMovementController;
import ib.fatninja.engine.ui.controls.Button;
import ib.fatninja.engine.ui.events.TouchHandler;
import ib.fatninja.managers.CoordinateManager;
import ib.fatninja.managers.SettingsManager;
import ib.fatninja.managers.SoundManager;
import ib.fatninja.managers.StyleManager;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {

	public GameLoopThread gameThread;
	private TouchHandler touchHandler;
	private IMovementController movementController;
	
    private SurfaceHolder holder;
    private MapBase currentMap;
	private boolean isFirstTime = true;
	private final int appleDelay = 50;
	private int appleDelayCounter = 0;
	private Apple appleMenu;
	private final int ticksPerLevel = 3000 ;
	private int ticksPerLevelCounter = ticksPerLevel;
	
	public GameView(Context context) throws IOException {
		super(context);
		touchHandler = new TouchHandler();
		movementController = SettingsManager.Instance().getMovementController();		
		gameThread = new GameLoopThread(this);
		holder = getHolder();		
		holder.addCallback(new SurfaceHolder.Callback() {
			// Destroy
			public void surfaceDestroyed(SurfaceHolder holder) {
				if(gameThread != null)
					gameThread.setPause();
				    SoundManager.Instance().stopGameSound();
            }

			public void surfaceCreated(SurfaceHolder holder) {
				  if(isFirstTime){
				      CollisionHandler.clear();
				      touchHandler.clear();
					  appleMenu = new Apple(
							  	 CoordinateManager.Instance().getScorePosition().x - 10
								,CoordinateManager.Instance().getScorePosition().y - CoordinateManager.Instance().getSpriteEdge() + 10);
					  currentMap = new Map0_0();
					  touchHandler.addElement(movementController);
					  gameThread.setRunning(true);
			          gameThread.start();	
					  isFirstTime = false;			
				  }
				  else
					  gameThread.setResume();
				  SoundManager.Instance().playGameSound();
			}
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}		
		});
	}
	
	@Override
	public void draw(Canvas c){
		FatNinja.Instance().setMovement(movementController.getMovement());	
		appleDelayCounter++;
		ticksPerLevelCounter--;

		if(appleDelayCounter >= appleDelay ){
			appleDelayCounter = 0;
			currentMap.addApple();
		}
		if(ticksPerLevelCounter == 0 || FatNinja.Instance().isDead){
			gameThread.setPause();
			ticksPerLevelCounter = ticksPerLevel;
		    appleDelayCounter = 0;
		    touchHandler.clear();

			Button gameOver = new Button(
					 CoordinateManager.Instance().getGameOverPosition().x
					, CoordinateManager.Instance().getGameOverPosition().y
					, 400
					, 35
					, FatNinja.Instance().isDead 
						? "GAME OVER"
						: "NEXT LEVEL"
					, StyleManager.Instance().getGameOverFontStyle()
					, touchHandler){				
				@Override
				public void onTouchClick(float x, float y){
					initObjects();	
				}
			};
			c.drawRGB(0, 0, 0);
			gameOver.onDrawObj(c);
			return;			
		}

		FatNinja.Instance().setStandardTicks();
		currentMap.beforeDrawObj();
		
		CollisionHandler.findCollision();
		currentMap.onDrawObj(c);				
		appleMenu.onDrawObj(c);
		movementController.onDrawObj(c);
		c.drawText(String.valueOf(FatNinja.Instance().getApples())
				, CoordinateManager.Instance().getScorePosition().x + CoordinateManager.Instance().getSpriteEdge()
				, CoordinateManager.Instance().getScorePosition().y
				, StyleManager.Instance().getScoreFontStyle());
		c.drawText(String.valueOf(ticksPerLevelCounter)
				, CoordinateManager.Instance().getScorePosition().x
				, CoordinateManager.Instance().getScorePosition().y + CoordinateManager.Instance().getSpriteEdge()
				, StyleManager.Instance().getScoreFontStyle());
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == android.view.MotionEvent.ACTION_DOWN)
			touchHandler.touch(event.getX(), event.getY());
		if(event.getAction() == android.view.MotionEvent.ACTION_UP)
			touchHandler.touchRelease(event.getX(), event.getY());
		return true;
	}	
	
	private void initObjects(){
		CollisionHandler.clear();
		touchHandler.clear();
		if(FatNinja.Instance().isDead){
			FatNinja.Instance().clear();
			currentMap = new Map0_0();
		}
		else
			currentMap = new Map0_1();
		touchHandler.addElement(movementController);
		gameThread.setResume();
	}
}
