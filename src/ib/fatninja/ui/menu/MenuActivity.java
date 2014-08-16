package ib.fatninja.ui.menu;

import ib.fatninja.managers.SettingsManager;
import ib.fatninja.ui.BaseActivity;
import android.view.View;

public class MenuActivity extends BaseActivity {

	@Override
	protected View getView() {
		return new MenuView(this);
	}	
	
	@Override
	public void onBackPressed() {
		SettingsManager.Instance().getActivity().finish();
		super.onBackPressed();
	}
}