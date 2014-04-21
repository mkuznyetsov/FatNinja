package ib.fatninja.ui.menu;

import ib.fatninja.engine.ui.Button;
import ib.fatninja.engine.ui.MenuButton;
import ib.fatninja.engine.ui.events.MenuTouchHandler;
import ib.fatninja.managers.CoordinateManager;
import ib.fatninja.managers.ResourceManager;
import ib.fatninja.managers.SettingsManager;
import ib.fatninja.managers.SoundManager;
import ib.fatninja.ui.game.GameActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MenuView extends SurfaceView {

	private MenuLoopThread menuLoopThread;

	private Background bg;
	private MenuButton startButton;
	private MenuButton exitButton;

	private MenuButton movieButton;
	private MenuButton joyStickButton;
	private MenuButton soundButton;
	
	private MenuView THIS;

	public MenuView(Context context) {
		super(context);
		THIS = this;
		if(menuLoopThread == null){
			menuLoopThread = new MenuLoopThread(this);
			InitObjects();
			menuLoopThread.setIsRunning(true);
			menuLoopThread.start();			
		}

		getHolder().addCallback(new SurfaceHolder.Callback() {
			// Destroy
			public void surfaceDestroyed(SurfaceHolder holder) {
				System.out.println("ON Destroyed");
				if(menuLoopThread != null)
					menuLoopThread.onPause();
				SoundManager.Instance().stopMenuSound();
			}

			// Start first
			public void surfaceCreated(SurfaceHolder holder) {
				menuLoopThread.onResume();
				setMovieCheckboxButton();
				setJoystickCheckboxButton();
				setSoundCheckboxButton();
				SoundManager.Instance().playMenuSound();
			}
			// Update
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == 0) {
			MenuTouchHandler.Instance().touch(event.getX(), event.getY());
		}
		return true;
	}
		
	@Override
	public void onDraw(Canvas c){
		bg.onDrawObj(c);
		startButton.onDrawObj(c);
		exitButton.onDrawObj(c);
		joyStickButton.onDrawObj(c);
		movieButton.onDrawObj(c);
		soundButton.onDrawObj(c);
	}
	
	private void InitObjects(){
		MenuTouchHandler.Instance().clear();
		bg = new Background();
		startButton = new MenuButton(30, 30, 270, 35, ResourceManager.Instance().getNewGameRes()) {
			@Override
			public void onTouchClick(float x, float y) {
				Intent myIntent = new Intent(THIS.getContext(), GameActivity.class);
				SettingsManager.Instance().getActivity().startActivity(myIntent);  
			}
		};

		exitButton = new MenuButton(CoordinateManager.Instance().getScreenWidth() - 170
				, CoordinateManager.Instance().getScreenHeight() - 100, 140, 35, ResourceManager.Instance().getExitRes()) {
			@Override
			public void onTouchClick(float x, float y) {
				menuLoopThread.setIsRunning(false);
				menuLoopThread.onResume();
				menuLoopThread = null;
				SettingsManager.Instance().getActivity().finish();
			}
		};
		
		movieButton = new MenuButton(CoordinateManager.Instance().getScreenWidth() - 100, 40, 50, 50) {
			@Override
			public void onTouchClick(float x, float y) {
				SettingsManager.Instance().isMovieEnabled = !SettingsManager.Instance().isMovieEnabled;
				setMovieCheckboxButton();
			}
		};
		setMovieCheckboxButton();
		
		joyStickButton = new MenuButton(CoordinateManager.Instance().getScreenWidth() - 100, 110, 50, 50) {
			@Override
			public void onTouchClick(float x, float y) {
				SettingsManager.Instance().isJoyStickEnabled = !SettingsManager.Instance().isJoyStickEnabled;
				setJoystickCheckboxButton();
			}
		};
		setJoystickCheckboxButton();
		
		soundButton = new MenuButton(CoordinateManager.Instance().getScreenWidth() - 100, 180, 50, 50) {
			@Override
			public void onTouchClick(float x, float y) {
				SettingsManager.Instance().isSoundEnabled = !SettingsManager.Instance().isSoundEnabled;
				setSoundCheckboxButton();
				if(SettingsManager.Instance().isSoundEnabled)
					SoundManager.Instance().playMenuSound();
				else
					SoundManager.Instance().stopMenuSound();
			}
		};
		setSoundCheckboxButton();
		
	}
	
	private void setMovieCheckboxButton(){
		setCheckboxButton(movieButton, SettingsManager.Instance().isMovieEnabled
				, ResourceManager.Instance().getMovieOnRes(), ResourceManager.Instance().getMovieOffRes());
	}

	private void setJoystickCheckboxButton(){
		setCheckboxButton(joyStickButton, SettingsManager.Instance().isJoyStickEnabled
				, ResourceManager.Instance().getJoyStickOnRes(), ResourceManager.Instance().getJoyStickOffRes());
	}

	private void setSoundCheckboxButton(){
		setCheckboxButton(soundButton, SettingsManager.Instance().isSoundEnabled
				, ResourceManager.Instance().getSoundOnRes(), ResourceManager.Instance().getSoundOffRes());
	}
	
	private void setCheckboxButton(Button btn, boolean isEnabled, Bitmap resourceChecked, Bitmap resourceUnchecked){
		if(isEnabled)
			btn.setBMP(resourceChecked);
		else
			btn.setBMP(resourceUnchecked);
	}
	
}
