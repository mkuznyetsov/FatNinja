package ib.fatninja.managers;

import ib.fatninja.ui.menu.MenuActivity;

public class SettingsManager {

	private static SettingsManager instance = null;
	
	public static SettingsManager Instance(){
		if(instance == null)
			instance = new SettingsManager();
		return instance;
	} 
	
	private MenuActivity mainActivity;

    public boolean isJoyStickEnabled = false;

    public boolean isMovieEnabled = true;

    public boolean isSoundEnabled = false;
	
	public void setMainActivity(MenuActivity activity){
		mainActivity = activity;
	}
	
	public MenuActivity getActivity(){
		return mainActivity;
	}

}
