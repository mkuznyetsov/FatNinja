package ib.fatninja.ui.menu;

import ib.fatninja.engine.ui.Button;
import ib.fatninja.engine.ui.GridLayout;
import ib.fatninja.managers.CoordinateManager;
import ib.fatninja.managers.ResourceManager;
import ib.fatninja.managers.SettingsManager;
import ib.fatninja.managers.SoundManager;
import ib.fatninja.ui.TouchHandler;
import ib.fatninja.ui.game.GameActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MenuView extends SurfaceView {

	private MenuLoopThread menuLoopThread;
	private TouchHandler touchHandler;
	private GridLayout gridLayout;
	
	private Background bg;
	private Button startButton;

	private Button movieButton;
	private Button joyStickButton;
	private Button soundButton;
	
	private MenuView THIS;

	public MenuView(Context context) {
		super(context);
		THIS = this;
		if(gridLayout == null)
			gridLayout = new GridLayout(14, 30);		
		if(touchHandler == null)
			touchHandler = new TouchHandler();
		if(menuLoopThread == null){
			menuLoopThread = new MenuLoopThread(this);
			initObjects();
			menuLoopThread.setRunning(true);
			menuLoopThread.start();			
		}

		getHolder().addCallback(new SurfaceHolder.Callback() {
			// Destroy
			public void surfaceDestroyed(SurfaceHolder holder) {
				System.out.println("ON Destroyed");
				if(menuLoopThread != null)
					menuLoopThread.setPause();
				SoundManager.Instance().stopMenuSound();
			}

			// Start first
			public void surfaceCreated(SurfaceHolder holder) {
				menuLoopThread.setResume();
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
			touchHandler.touch(event.getX(), event.getY());
		}
		return true;
	}
		
	@Override
	public void draw(Canvas c){
		bg.onDrawObj(c);
		startButton.onDrawObj(c);
		joyStickButton.onDrawObj(c);
		movieButton.onDrawObj(c);
		soundButton.onDrawObj(c);
	}
	
	private void initObjects(){
		touchHandler.clear();
		bg = new Background();
		PointF position = gridLayout.getPosition(1, 1);
		startButton = new Button(position.x, position.y, 270, 35
				, ResourceManager.Instance().getNewGameRes()
				, touchHandler) {
			@Override
			public void onTouchClick(float x, float y) {
				Intent myIntent = new Intent(THIS.getContext(), GameActivity.class);
				SettingsManager.Instance().getActivity().startActivity(myIntent);  
			}
		};
		
		movieButton = new Button(CoordinateManager.Instance().getScreenWidth() - 100, 40, 50, 50
				, touchHandler) {
			@Override
			public void onTouchClick(float x, float y) {
				SettingsManager.Instance().setMovieEnabled(!SettingsManager.Instance().isMovieEnabled());
				setMovieCheckboxButton();
			}
		};
		setMovieCheckboxButton();
		
		joyStickButton = new Button(CoordinateManager.Instance().getScreenWidth() - 100, 110, 50, 50
				, touchHandler) {
			@Override
			public void onTouchClick(float x, float y) {
				SettingsManager.Instance().setJoyStickEnabled(!SettingsManager.Instance().isJoyStickEnabled());
				setJoystickCheckboxButton();
			}
		};
		setJoystickCheckboxButton();
		
		soundButton = new Button(CoordinateManager.Instance().getScreenWidth() - 100, 180, 50, 50
				, touchHandler) {
			@Override
			public void onTouchClick(float x, float y) {
				SettingsManager.Instance().setSoundEnabled(!SettingsManager.Instance().isSoundEnabled());
				setSoundCheckboxButton();
				if(SettingsManager.Instance().isSoundEnabled())
					SoundManager.Instance().playMenuSound();
				else
					SoundManager.Instance().stopMenuSound();
			}
		};
		setSoundCheckboxButton();
		
	}

	private void setMovieCheckboxButton(){
		setCheckboxButton(movieButton, SettingsManager.Instance().isMovieEnabled()
				, ResourceManager.Instance().getMovieOnRes()
				, ResourceManager.Instance().getMovieOffRes());
	}

	private void setJoystickCheckboxButton(){
		setCheckboxButton(joyStickButton, SettingsManager.Instance().isJoyStickEnabled()
				, ResourceManager.Instance().getJoyStickOnRes()
				, ResourceManager.Instance().getJoyStickOffRes());
	}

	private void setSoundCheckboxButton(){
		setCheckboxButton(soundButton, SettingsManager.Instance().isSoundEnabled()
				, ResourceManager.Instance().getSoundOnRes(), ResourceManager.Instance().getSoundOffRes());
	}
	
	private void setCheckboxButton(Button btn, boolean isEnabled, Bitmap resourceChecked, Bitmap resourceUnchecked){
		if(isEnabled)
			btn.setBMP(resourceChecked);
		else
			btn.setBMP(resourceUnchecked);
	}
	
}
