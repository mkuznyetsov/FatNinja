package ib.fatninja.ui.game;

import ib.fatninja.managers.ResourceManager;
import ib.fatninja.managers.SettingsManager;
import ib.fatninja.ui.LoopThread;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class GameLoopThread extends LoopThread {
	
	public GameLoopThread(SurfaceView view) {
		super(view);
	}

	@Override
	public void onTick(Canvas c){
		if(SettingsManager.Instance().isMovieEnabled())
		{
			try {
				ShowMovieFrame(c, ResourceManager.Instance().getMovieFrame(0), 1500);
				ShowMovieFrame(c, ResourceManager.Instance().getMovieFrame(1), 1500);
				ShowMovieFrame(c, ResourceManager.Instance().getMovieFrame(2), 1500);
				ShowMovieFrame(c, ResourceManager.Instance().getMovieFrame(3), 1500);
				ShowMovieFrame(c, ResourceManager.Instance().getMovieFrame(4), 1500);
				ShowMovieFrame(c, ResourceManager.Instance().getMovieFrame(5), 1500);
				ShowMovieFrame(c, ResourceManager.Instance().getMovieFrame(6), 1500);
				ShowMovieFrame(c, ResourceManager.Instance().getMovieFrame(7), 1500);
				ShowMovieFrame(c, ResourceManager.Instance().getMovieFrame(8), 800);
				ShowMovieFrame(c, ResourceManager.Instance().getMovieFrame(9), 500);
				ShowMovieFrame(c, ResourceManager.Instance().getMovieFrame(10), 500);
				ShowMovieFrame(c, ResourceManager.Instance().getMovieFrame(11), 500);				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
			finally {
				SettingsManager.Instance().setMovieEnabled(false);
			}
		} 
	}
	
	private void ShowMovieFrame(Canvas c, Bitmap bmp, int delay) throws InterruptedException{
		if(bmp == null)
			return;
		c.drawBitmap(bmp,0,0,null);
		view.getHolder().unlockCanvasAndPost(c); 
		sleep(delay);
 	    c = view.getHolder().lockCanvas();
 	   	bmp.recycle();
	}
	
}
