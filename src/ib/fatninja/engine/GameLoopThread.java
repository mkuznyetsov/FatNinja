package ib.fatninja.engine;

import ib.fatninja.managers.ResourceManager;
import ib.fatninja.managers.SettingsManager;
import ib.fatninja.ui.GameView;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameLoopThread extends Thread {
	
	private boolean isRunning = false;
	private GameView gv;
	
	private Object mPauseLock;
	private boolean mPaused;
		
	public GameLoopThread(GameView _gv){
		this.gv = _gv;
	    mPauseLock = new Object();
	    mPaused = false;
	}
		
	public void setRunning(boolean run) {
		isRunning = run;
	}
	
	@Override
	public void run() {
		while (isRunning){
	        Canvas c = null; 
	        try{
	            c = gv.getHolder().lockCanvas(); 
	            synchronized (gv.getHolder()){ 
	            	if(c != null){
	            	   if(SettingsManager.Instance().isMovieEnabled)
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
	    	    			}
	    	    			SettingsManager.Instance().isMovieEnabled = false;
	    	    		} 
	            	   if(c != null)
	            		   gv.onDraw(c); 
	            	}
	            }
	        }finally{
	            if(c != null) {
	            	gv.getHolder().unlockCanvasAndPost(c); 
	            }
	        }try {
	        		sleep(16);
	        }catch(Exception e){

	        }
	        synchronized (mPauseLock) {
                while (mPaused) {
                    try {
                        mPauseLock.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }		
		}
	}
	
	private void ShowMovieFrame(Canvas c, Bitmap bmp, int delay) throws InterruptedException{
		if(bmp == null)
			return;
		c.drawBitmap(bmp,0,0,null);
    	gv.getHolder().unlockCanvasAndPost(c); 
		sleep(delay);
 	    c = gv.getHolder().lockCanvas();
 	   	bmp.recycle();
	}
	
    public void onPause() {
        synchronized (mPauseLock) {
            mPaused = true;
        }
    }

    public void onResume() {
        synchronized (mPauseLock) {
            mPaused = false;
            mPauseLock.notifyAll();
        }
    }
}