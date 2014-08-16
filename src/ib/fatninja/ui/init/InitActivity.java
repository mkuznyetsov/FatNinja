package ib.fatninja.ui.init;

import ib.fatninja.managers.CoordinateManager;
import ib.fatninja.managers.SettingsManager;
import ib.fatninja.ui.BaseActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

public class InitActivity extends BaseActivity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		SettingsManager.Instance().setMainActivity(this);
		Display display = getWindowManager().getDefaultDisplay();
		int x = display.getWidth();
		int y = display.getHeight();
		CoordinateManager.Instance().setScreenWidth(Math.max(x, y));
		CoordinateManager.Instance().setScreenHeight(Math.min(x, y));
		super.onCreate(savedInstanceState);	
	}

	@Override
	protected View getView() {
		return new InitView(this);
	}
}
