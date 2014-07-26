package ib.fatninja.ui.menu;

import ib.fatninja.ui.LoopThread;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class MenuLoopThread extends LoopThread {

	public MenuLoopThread(SurfaceView view) {
		super(view);
	}

	@Override
	protected void onTick(Canvas c) {
		
	}
}
