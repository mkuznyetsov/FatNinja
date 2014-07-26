package ib.fatninja.managers;

import android.graphics.Paint;
import android.graphics.Typeface;

public class StyleManager {

	private StyleManager(){}
	
	private static StyleManager instance;
	
	public static StyleManager Instance(){
		if(instance == null)
			instance = new StyleManager();
		return instance;
	}
	
	private Paint menuFontStyle;

	private Paint gameOverFontStyle;
	
	public Paint getScoreFontStyle(){
		if(menuFontStyle == null){
			menuFontStyle = new Paint();
			Typeface font = Typeface.createFromAsset(
					SettingsManager.Instance().getActivity().getAssets(), "fonts/BITFONT.ttf");  
			menuFontStyle.setTypeface(font);
			menuFontStyle.setTextSize(35);
			menuFontStyle.setARGB(200, 240, 240, 240);
		}
		return menuFontStyle;		
	}
	
	public Paint getGameOverFontStyle(){
		if(gameOverFontStyle == null){
			gameOverFontStyle = new Paint();
			Typeface font = Typeface.createFromAsset(
					SettingsManager.Instance().getActivity().getAssets(), "fonts/BITFONT.ttf");  
			gameOverFontStyle.setTypeface(font);
			gameOverFontStyle.setTextSize(80);
			gameOverFontStyle.setARGB(255, 200, 55, 30);
		}
		return gameOverFontStyle;		
	}
	
}
