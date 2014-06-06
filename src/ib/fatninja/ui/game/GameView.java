package ib.fatninja.ui.game;

import java.io.IOException;
import ib.fatninja.base.AMovableSpriteObject.eMovement;
import ib.fatninja.base.acive.Player.FatNinja;
import ib.fatninja.base.map.MapBase;
import ib.fatninja.base.map.forest0.Map0_0;
import ib.fatninja.base.map.forest0.Map0_1;
import ib.fatninja.base.terra.Apple;
import ib.fatninja.engine.collision.CollisionHandler;
import ib.fatninja.engine.ui.GameButton;
import ib.fatninja.engine.ui.JoyPad4Direction;
import ib.fatninja.managers.CoordinateManager;
import ib.fatninja.managers.ResourceManager;
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
	
    private SurfaceHolder holder;
    private MapBase currentMap;
	private JoyPad4Direction joyStick;
	private boolean isFirstTime = true;
	private final int appleDelay = 50;
	private int appleDelayCounter = 0;
	private Apple appleMenu;
	private final int ticksPerLevel = 3000 ;
	private int ticksPerLevelCounter = ticksPerLevel ;
	
	public GameView(Context context) throws IOException {
		super(context);
		gameThread = new GameLoopThread(this);
		holder = getHolder();		
		holder.addCallback(new SurfaceHolder.Callback() {
			// Destroy
			public void surfaceDestroyed(SurfaceHolder holder) {
				if(gameThread != null)
					gameThread.onPause();
				    SoundManager.Instance().stopGameSound();
            }

			public void surfaceCreated(SurfaceHolder holder) {
				  if(isFirstTime){
				      CollisionHandler.clearList();
				      GameTouchHandler.clearList();
					  appleMenu = new Apple(
							  	 CoordinateManager.Instance().getScorePosition().x - 10
								,CoordinateManager.Instance().getScorePosition().y - CoordinateManager.Instance().getSpriteEdge() + 10);
					  currentMap = new Map0_0();
					  if(SettingsManager.Instance().isJoyStickEnabled)
						  joyStick = new JoyPad4Direction(
							  CoordinateManager.Instance().getJoystickPosition().x
							, CoordinateManager.Instance().getJoystickPosition().y
							, ResourceManager.Instance().getJoyStick().getWidth()
							, ResourceManager.Instance().getJoyStick().getHeight());
					  gameThread.setRunning(true);
			          gameThread.start();	
					  isFirstTime = false;			
				  }
				  else
					  gameThread.onResume();
				  SoundManager.Instance().playGameSound();
			}
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}		
		});
	}
	
	private void addAppleInRandomPlace(){
		currentMap.addApple();
	}	
	
	@Override
	public void onDraw(Canvas c){
		if(SettingsManager.Instance().isJoyStickEnabled){
			eMovement joyStickMovement = joyStick.getMovement();
			if(joyStickMovement != eMovement.NONE){				
				FatNinja.Instance().setMovement(joyStickMovement);
				joyStick.setDefault();			
			}
		}
		appleDelayCounter++;
		ticksPerLevelCounter--;

		if(appleDelayCounter >= appleDelay ){
			appleDelayCounter = 0;
			addAppleInRandomPlace();
		}
		if(ticksPerLevelCounter == 0 || FatNinja.Instance().isDead){
			gameThread.onPause();
			ticksPerLevelCounter = ticksPerLevel;
		    appleDelayCounter = 0;
			GameButton gameOver = new GameButton(
					CoordinateManager.Instance().getGameOverPosition().x
					,CoordinateManager.Instance().getGameOverPosition().y, 400, 35,
					ResourceManager.Instance().getGameOverRes());
			GameButton newGame = new GameButton(
					 CoordinateManager.Instance().getNewGamePosition().x
					,CoordinateManager.Instance().getNewGamePosition().y, 400, 35, FatNinja.Instance().isDead 
						? ResourceManager.Instance().getNewGameTouchedRes()
						: ResourceManager.Instance().getNewGameRes()){				
				@Override
				public void onTouchClick(float x, float y){
					CollisionHandler.clearList();
				    GameTouchHandler.clearList();
				    currentMap.clearItems();
					if(FatNinja.Instance().isDead)
						currentMap = new Map0_0();
					else
						currentMap = new Map0_1();
					gameThread.onResume();
				}
			};
			c.drawRGB(0, 0, 0);
			gameOver.onDrawObj(c);
			newGame.onDrawObj(c);
			return;			
		}

		FatNinja.Instance().setStandardTicks();
		currentMap.beforeDrawObj();
		
		CollisionHandler.findCollision();
		currentMap.onDrawObj(c);				
		appleMenu.onDrawObj(c);
				
		if(SettingsManager.Instance().isJoyStickEnabled)
			joyStick.onDrawObj(c);
		c.drawText(String.valueOf(FatNinja.Instance().getApples())
				, CoordinateManager.Instance().getScorePosition().x + CoordinateManager.Instance().getSpriteEdge()
				, CoordinateManager.Instance().getScorePosition().y, StyleManager.Instance().getScoreFontStyle());
		c.drawText(String.valueOf(ticksPerLevelCounter)
				, CoordinateManager.Instance().getScorePosition().x
				, CoordinateManager.Instance().getScorePosition().y + CoordinateManager.Instance().getSpriteEdge(), StyleManager.Instance().getScoreFontStyle());
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == android.view.MotionEvent.ACTION_DOWN)
			GameTouchHandler.touch(event.getX(), event.getY());
		if(event.getAction() == android.view.MotionEvent.ACTION_UP)
			GameTouchHandler.touchRelease(event.getX(), event.getY());
		return true;
	}	
		
}