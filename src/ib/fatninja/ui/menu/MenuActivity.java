package ib.fatninja.ui.menu;

import ib.fatninja.managers.CoordinateManager;
import ib.fatninja.managers.SettingsManager;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Display;
import android.view.Window;
import android.os.Bundle;

public class MenuActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Display display = getWindowManager().getDefaultDisplay();
        // higher side = width
		CoordinateManager.Instance().setScreenWidth(display.getWidth() > display.getHeight() ? display.getWidth() : display.getHeight());
        // shortest side = height
		CoordinateManager.Instance().setScreenHeight(display.getHeight() < display.getWidth() ? display.getHeight() : display.getWidth());	
		
		SettingsManager.Instance().setMainActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(new MenuView(this));
	}

}