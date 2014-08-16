package ib.fatninja.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import ib.fatninja.ui.BaseActivity;

public class SettingsManager {
	
	private SettingsManager(){	}
	
	private static SettingsManager instance = null;
	
	public static SettingsManager Instance(){
		if(instance == null)
			instance = new SettingsManager();
		return instance;
	} 
	
	private BaseActivity mainActivity;
	private SharedPreferences sharedPreferences; 

	private boolean isJoyStickEnabled = false;
    private boolean isMovieEnabled = true;
    private boolean isSoundEnabled = false;
	
	public void setMainActivity(BaseActivity activity){
		mainActivity = activity;
		loadSettings();
	}
	
	public BaseActivity getActivity(){
		return mainActivity;
	}

    public boolean isJoyStickEnabled() {
		return isJoyStickEnabled;
	}

	public void setJoyStickEnabled(boolean isJoyStickEnabled) {
		this.isJoyStickEnabled = isJoyStickEnabled; 
		save("isJoyStickEnabled", isJoyStickEnabled);
	}

	public boolean isMovieEnabled() {
		return isMovieEnabled;
	}

	public void setMovieEnabled(boolean isMovieEnabled) {
		this.isMovieEnabled = isMovieEnabled;
		save("isMovieEnabled", isMovieEnabled);
	}

	public boolean isSoundEnabled() {
		return isSoundEnabled;
	}

	public void setSoundEnabled(boolean isSoundEnabled) {
		this.isSoundEnabled = isSoundEnabled;
		save("isSoundEnabled", isSoundEnabled);
	}
	
	private void save(String key, boolean val){
		Editor edit = sharedPreferences.edit();
		edit.putBoolean(key, val);
		edit.commit();
	}
	
	private void loadSettings(){
		sharedPreferences = mainActivity.getPreferences(Context.MODE_PRIVATE);
		setMovieEnabled(sharedPreferences.getBoolean("isMovieEnabled", true));
		setJoyStickEnabled(sharedPreferences.getBoolean("isJoyStickEnabled", true));
		setSoundEnabled(sharedPreferences.getBoolean("isSoundEnabled", true));
	}

}
