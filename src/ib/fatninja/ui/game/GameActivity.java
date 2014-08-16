package ib.fatninja.ui.game;

import ib.fatninja.ui.BaseActivity;
import java.io.IOException;
import android.view.View;

public class GameActivity extends BaseActivity{

	private GameView gameView;
	
	@Override
	public void onBackPressed() {
		gameView.gameThread.setResume();
		gameView.gameThread.setRunning(false);
		gameView.gameThread = null;
		super.onBackPressed();
	}

	@Override
	protected View getView() {
		try {
			gameView = new GameView(this);
		} catch (IOException e) {
			e.printStackTrace();
		};
		return gameView;
	}
}
