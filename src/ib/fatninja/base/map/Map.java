package ib.fatninja.base.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import ib.fatninja.base.acive.Player.FatNinja;
import ib.fatninja.base.terra.Apple;
import ib.fatninja.base.terra.Bush;
import ib.fatninja.base.terra.Grass;
import ib.fatninja.base.terra.Ground;
import ib.fatninja.base.terra.Hole;
import ib.fatninja.base.terra.Tree;
import ib.fatninja.engine.collision.CollisionHandler;
import ib.fatninja.engine.collision.ICollisionable;
import ib.fatninja.engine.ui.IDrawable;
import ib.fatninja.engine.ui.events.ITouchable;
import ib.fatninja.managers.CoordinateManager;
import ib.fatninja.managers.SettingsManager;
import ib.fatninja.ui.game.GameTouchHandler;
import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class Map implements ITouchable{

	private int mapID;	
	private float RBPointX = 0;
	private float RBPointY = 0;
	private FatNinja fatNinja ;
	private final int frameSize = CoordinateManager.Instance().getTileEdge();
	public boolean[][] availableitems = 
			new boolean[CoordinateManager.Instance().getTilesOnScreenWidth()]
					[CoordinateManager.Instance().getTilesOnScreenHeight()];
	
	private List<IDrawable> items = new ArrayList<IDrawable>();
	
	private Apple[] apples = new Apple[20];
	
	public Map(){
		this.fatNinja = new FatNinja();
		CollisionHandler.addCollisionableElement(fatNinja);
		initApples();
		GameTouchHandler.addElement(this);
	}
	
	public void clearItems(){
		items = new ArrayList<IDrawable>();		
	}
	
	public void initMapFromFile(int _mapID){
		this.mapID = _mapID; 
		InputStream is = SettingsManager.Instance().getActivity()
				.getResources().openRawResource(mapID);
		String str="";
		StringBuffer buf = new StringBuffer();			
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		if (is!=null) {							
			try {
				while ((str = reader.readLine()) != null) {	
					buf.append(str + "\n" );
				}
			} catch (IOException e) {
				e.printStackTrace();
			}				
		}	
		String result = buf.toString();
		int i = 0;
		int j = 0;
		for(char ch : result.toCharArray()){
			if(ch ==','){
				i++;
			}
			if(ch == '\n'){
				i = 0;
				j++;
			}
			if(availableitems.length <= i || availableitems[0].length <= j)
				continue;
			if(ch == '1'){
				this.addItem(new Tree(i*frameSize,j*frameSize),true);
				availableitems[i][j] = false;
			}
			else if(ch == '2'){
				this.addItem(new Bush(i*frameSize,j*frameSize),true);
				availableitems[i][j] = true;
			}
			else if(ch == '3'){
				this.addItem(new Hole(i*frameSize,j*frameSize), false);
				availableitems[i][j] = true;
			}
			else if(ch == '4'){
				this.addItem(new Grass(i*frameSize,j*frameSize), false);
				availableitems[i][j] = true;
			}
			else if(ch == '5'){
				this.addItem(new Ground(i*frameSize,j*frameSize), false);
				availableitems[i][j] = false;
			}
				
		}
	}
		
	public FatNinja getHero(){
		return fatNinja;
	}	
	
	public void addItem(IDrawable item, boolean addToCollisionHandler){
		items.add(item);
		ICollisionable colItem = (ICollisionable.class.cast(item));
		if(colItem != null && addToCollisionHandler)
		{
			CollisionHandler.addCollisionableElement(colItem);
		}
	}

	public void onTouchClick(float x, float y) {
		if(!SettingsManager.Instance().isJoyStickEnabled)
			fatNinja.coordinator(x, y);
	}	

	public void onTouchRelease(float x, float y) {
	}	
	
	@SuppressLint("DrawAllocation")
	public void onDrawObj(Canvas c) {
		for(int i = 0; i< items.size(); i++){
			items.get(i).onDrawObj(c);
		}
		for(int i = 0; i< apples.length; i++){
			apples[i].onDrawObj(c);
		}
		onDrawNinja(c);
	}

	@SuppressLint("DrawAllocation")
	public void onDrawNinja(Canvas c){
		getHero().onDrawObj(c);
	}

	public float getX() {
		return 0;
	}

	public float getY() {
		return 0;
	}

	public float getWidth() {
		return CoordinateManager.Instance().getScreenWidth();
	}

	public float getHeight() {
		return CoordinateManager.Instance().getScreenHeight();
	}

	public float getRBPointX() {
		if(RBPointX == 0)
			RBPointX = getX() + getWidth();
		return RBPointX;
	}

	public float getRBPointY() {
		if(RBPointY == 0)
			RBPointY = getY() + getHeight();
		return RBPointY;
	}
	
	public void addApple(){
		double j = Math.random() * CoordinateManager.Instance().getTilesOnScreenHeight();
		double i = Math.random() * CoordinateManager.Instance().getTilesOnScreenWidth();
		if(availableitems[(int)i][(int)j]){
			Apple apple = getFreeApple();
			if(apple == null)
				return;
			apple.setFreeToPlace(false);
			apple.setX((int)i*CoordinateManager.Instance().getTileEdge());
			apple.setY((int)j*CoordinateManager.Instance().getTileEdge());
		}
		else
			addApple();
	}
	
	private void initApples(){
		for(int i = 0; i < apples.length; i++){
			apples[i] = new Apple(-100, -100);
			CollisionHandler.addCollisionableElement(apples[i]);
		}
	}
	
	private Apple getFreeApple(){
		for(int i = 0; i < apples.length; i++){
			if(apples[i].isFreeToPlace())
				return apples[i];
		}
		return null;
	}
}
