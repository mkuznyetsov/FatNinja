package ib.fatninja.ui;

import android.graphics.Canvas;
import android.view.SurfaceView;

public abstract class LoopThread extends Thread {

	protected boolean isRunning = false;
	protected SurfaceView view;

	private Object pauseLock;
	private boolean paused;

	public LoopThread(SurfaceView view) {
		this.view = view;
		pauseLock = new Object();
		paused = false;
	}

	public void setRunning(boolean running) {
		isRunning = running;
	}

	public boolean getIsRunning() {
		return isRunning;
	}

	@Override
	public void run() {
		while (isRunning) {
			Canvas c = null;
			try {
				c = view.getHolder().lockCanvas();
				synchronized (view.getHolder()) {
					if (c != null) {
						onTick(c);
						view.draw(c);
					}
				}
			} finally {
				if (c != null) {
					view.getHolder().unlockCanvasAndPost(c);
				}
			}
			try {
				sleep(16);
			} catch (Exception e) {

			}
			synchronized (pauseLock) {
				while (paused) {
					try {
						pauseLock.wait();
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}
	
    public void setPause() {
        synchronized (pauseLock) {
            paused = true;
        }
    }

    public void setResume() {
        synchronized (pauseLock) {
        	paused = false;
        	pauseLock.notifyAll();
        }
    }
    
	protected abstract void onTick(Canvas c);

}
