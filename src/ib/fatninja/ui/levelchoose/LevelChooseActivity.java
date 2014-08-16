package ib.fatninja.ui.levelchoose;

import ib.fatninja.ui.BaseActivity;
import android.view.View;

public class LevelChooseActivity extends BaseActivity {
	
	@Override
	protected View getView() {
		return new LevelChooseView(this);
	}
}
