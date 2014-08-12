package ib.fatninja.ui.game;

import java.io.IOException;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity{
	
	private GameView gameView ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
				, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		try {
			gameView = new GameView(this);
			setContentView(gameView);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onBackPressed() {
		gameView.gameThread.resume();
		gameView.gameThread.setRunning(false);
		gameView.gameThread = null;
		super.onBackPressed();
	}
}
