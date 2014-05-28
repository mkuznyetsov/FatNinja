package ib.fatninja.ui.game;

import ib.fatninja.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ib.fatninja.base.AMovableSpriteObject.eMovement;
import ib.fatninja.base.acive.BaseActiveObj;
import ib.fatninja.base.acive.NPC.Enemy.Bear;
import ib.fatninja.base.acive.NPC.Enemy.Troll;
import ib.fatninja.base.acive.NPC.Enemy.Woolf;
import ib.fatninja.base.map.Map;
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
    private Map currentMap;
	private JoyPad4Direction joyStick;
	private List<BaseActiveObj> evils = new ArrayList<BaseActiveObj>();
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
					  initObjects(R.raw.level1);
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
				currentMap.getHero().setMovement(joyStickMovement);
				joyStick.setDefault();			
			}
		}
		appleDelayCounter++;
		ticksPerLevelCounter--;

		if(appleDelayCounter >= appleDelay ){
			appleDelayCounter = 0;
			addAppleInRandomPlace();
		}
		if(ticksPerLevelCounter == 0 || currentMap.getHero().isDead){
			gameThread.onPause();
			ticksPerLevelCounter = ticksPerLevel;
		    appleDelayCounter = 0;
			GameButton gameOver = new GameButton(
					CoordinateManager.Instance().getGameOverPosition().x
					,CoordinateManager.Instance().getGameOverPosition().y, 400, 35,
					ResourceManager.Instance().getGameOverRes());
			GameButton newGame = new GameButton(
					 CoordinateManager.Instance().getNewGamePosition().x
					,CoordinateManager.Instance().getNewGamePosition().y, 400, 35, currentMap.getHero().isDead 
						? ResourceManager.Instance().getNewGameTouchedRes()
						: ResourceManager.Instance().getNewGameRes()){				
				@Override
				public void onTouchClick(float x, float y){
					CollisionHandler.clearList();
				    GameTouchHandler.clearList();
				    currentMap.clearItems();
					evils = new ArrayList<BaseActiveObj>();
					if(currentMap.getHero().isDead)
						initObjects(R.raw.level1);
					else
						initObjects(R.raw.level2);
					gameThread.onResume();
				}
			};
			c.drawRGB(0, 0, 0);
			gameOver.onDrawObj(c);
			newGame.onDrawObj(c);
			return;			
		}

		currentMap.getHero().setStandardTicks();

		for(int i = 0 ; i< evils.size();i++){
			evils.get(i).setStandardTicks();
		}
		CollisionHandler.findCollision();
		currentMap.onDrawObj(c);
		for(int i = 0 ; i< evils.size();i++){
			evils.get(i).onDrawObj(c);
		}
		appleMenu.onDrawObj(c);
		if(SettingsManager.Instance().isJoyStickEnabled)
			joyStick.onDrawObj(c);
		c.drawText(String.valueOf(currentMap.getHero().getApples())
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
	
	private void initObjects(int mapID){   
		if(SettingsManager.Instance().isJoyStickEnabled)
			joyStick = new JoyPad4Direction(CoordinateManager.Instance().getJoystickPosition().x, CoordinateManager.Instance().getJoystickPosition().y
					, ResourceManager.Instance().getJoyStick().getWidth()
					, ResourceManager.Instance().getJoyStick().getHeight());
		currentMap = new Map();
		currentMap.initMapFromFile(mapID);

        for(int i = 0; i< 10; i++)
            addEvil(new Woolf(), i);

		for(int i = 0; i< 10; i++)
            addEvil(new Bear(), i);

		for(int i = 0; i< 10; i++)
            addEvil(new Troll(), i);
	}

    private void addEvil(BaseActiveObj evil, int i){
        int rndMovement = (int)(Math.random() * 4);
        int rndX = (int)(Math.random() * CoordinateManager.Instance().getScreenWidth());
        int rndY = (int)(Math.random() * CoordinateManager.Instance().getScreenHeight());

        evil.setX(rndX*i);
        evil.setY(rndY*i);
        evil.setMovement(getMovement(rndMovement));

        evils.add(evil);
        CollisionHandler.addCollisionableElement(evil);
    }
	
	private eMovement getMovement(int number){
		switch (number)
		{
			case 0:
				return eMovement.LEFT;
			case 1:
				return eMovement.UP;
			case 2:
				return eMovement.LEFT;
			case 3:
			default:
				return eMovement.DOWN;
		}
	}
}