package ib.fatninja.ui.menu;

import ib.fatninja.managers.CoordinateManager;
import ib.fatninja.managers.SettingsManager;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.os.Bundle;

public class MenuActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SettingsManager.Instance().setMainActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		Point size = new Point();
		Display display = getWindowManager().getDefaultDisplay();
		size.x = display.getWidth();
		size.y = display.getHeight();
        // higher side = width
		CoordinateManager.Instance().setScreenWidth(Math.max(size.x, size.y));
        // shortest side = height
		CoordinateManager.Instance().setScreenHeight(Math.min(size.x, size.y));	
		setContentView(new MenuView(this));
	}
}