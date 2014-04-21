package ib.fatninja.ui.menu;

import android.graphics.Canvas;

public class MenuLoopThread extends Thread {

	MenuView mv;

	private Object mPauseLock;
	private boolean mPaused = false;
	private boolean isRunning = false;


	public MenuLoopThread(MenuView _mv) {
		this.mv = _mv;
	    mPauseLock = new Object();
	}

	public void setIsRunning(boolean isRunnging) {
		this.isRunning = isRunnging;
	}

	public boolean getIsRunning() {
		return isRunning;
	}

	public void onClose() {
		isRunning = false;
	}

	public void run() {
		while (isRunning) {
			Canvas c = null; 
			try {
				c = mv.getHolder().lockCanvas();
				synchronized (mv.getHolder()) {
					if (c != null)
						mv.onDraw(c);
					try {
						Thread.sleep(16);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} finally {
				if (c != null)
					mv.getHolder().unlockCanvasAndPost(c);
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
