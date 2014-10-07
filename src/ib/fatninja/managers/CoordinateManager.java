package ib.fatninja.managers;

import android.graphics.Point;

/**
* this class contains information about screen resolution,
* canvas size, standard sprite objects size, surfaceView size, 
* standard buttons size,panels size etc. according to device resolution
**/

public class CoordinateManager {

	private final int SpritePercentOfTile = 60;

	private final int SpriteCollisionErrorPercentOfTile = 12;
	
	private final int SpriteStepPerTile = 7;
	
	private final int SmallestSideTiles = 10;
	
	private final int JoyStickPercentOfSmallestSide = 45;
		
	private CoordinateManager(){}
	
	private static CoordinateManager instance;
	
	public static CoordinateManager Instance(){
		if(instance == null)
			instance = new CoordinateManager();
		return instance;
	}
	
	private int screenWidth;
	private int screenHeight;
	
	private int tileEdge = -1;
	private int tilesOnScreenHeight = -1;
	private int tilesOnScreenWidth = -1;
	private int spriteEdge = -1;	
	private int collisionError = -1;	
	private int spriteStep = -1;	
	private int joyStickEdge = -1;
	
	private Point joyStickPosition;
	private Point scorePosition;
	private Point newGamePosition;
	private Point gameOverPosition;
	
	public int getJoyStickEdge(){
		if(joyStickEdge == -1){
			joyStickEdge = JoyStickPercentOfSmallestSide * getSmallestSide() / 100;
		}
		return joyStickEdge;
	}
	
	public Point getNewGamePosition(){
		if(newGamePosition == null){
			newGamePosition = new Point(
					getScreenWidth() / 2 - ResourceManager.Instance().getNewGameRes().getWidth()/2
					, getScreenHeight() / 2 - ResourceManager.Instance().getNewGameRes().getHeight()/2);
		}
		return newGamePosition;
	}
	
	public Point getGameOverPosition(){
		if(gameOverPosition == null){
			gameOverPosition = new Point(
					getScreenWidth() / 2 - ResourceManager.Instance().getGameOverRes().getWidth()/2
					, getScreenHeight() / 2 - ResourceManager.Instance().getGameOverRes().getHeight()/2 - 40);
		}
		return gameOverPosition;
	}
	
	public Point getJoystickPosition(){
		if(joyStickPosition == null)
			joyStickPosition = new Point( getScreenWidth()- getJoyStickEdge() - 5,getScreenHeight() - getJoyStickEdge() - 5);
		return joyStickPosition;
	}
	
	public Point getScorePosition(){
		if(scorePosition == null)
			scorePosition = new Point( getScreenWidth() - 100, 40);
		return scorePosition;
	}
	
	public int getSpriteStep(){
		if(spriteStep == -1){
			spriteStep = getTileEdge() / SpriteStepPerTile;
		}
		return spriteStep;
	}	
	
	public int getSpriteEdge(){
		if(spriteEdge == -1){
			spriteEdge = SpritePercentOfTile * getTileEdge() / 100;
		}
		return spriteEdge;
	}
	
	public int getCollisionError(){
		if(collisionError == -1){
			collisionError = SpriteCollisionErrorPercentOfTile * getTileEdge() / 100;
		}		
		return collisionError;
	}
	
	public int getTileEdge(){
		if(tileEdge == -1)
		{
			tileEdge = getSmallestSide() / SmallestSideTiles;
		}
		return tileEdge;
	}
	
	public int getTilesOnScreenHeight(){
		if(tilesOnScreenHeight == -1){
			float remainder = screenHeight % getTileEdge();
			tilesOnScreenHeight = screenHeight / getTileEdge();
			if(remainder > 0.1f)
				tilesOnScreenHeight++;				
		}
		return tilesOnScreenHeight;
	}
		
	public int getTilesOnScreenWidth(){
		if(tilesOnScreenWidth == -1){
			float remainder = screenWidth % getTileEdge();
			tilesOnScreenWidth = screenWidth / getTileEdge();
			if(remainder > 0.1f)
				tilesOnScreenWidth++;
		}
		return tilesOnScreenWidth;
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public  void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	private int getSmallestSide(){
		return Math.min(screenWidth, screenHeight);
	}
	
}
