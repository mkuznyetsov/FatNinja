package ib.fatninja.ui.levelchoose;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

/**
 * created ate: 1/26/14
 * created time: 1:08 PM
 * by ndakota
 */
public class LevelChooseActivity extends Activity {
	
	private int x;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new LevelChooseView(this));
    }
}
