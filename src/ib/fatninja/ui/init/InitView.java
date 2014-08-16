package ib.fatninja.ui.init;

import ib.fatninja.managers.SettingsManager;
import ib.fatninja.ui.menu.MenuActivity;
import android.content.Context;
import android.content.Intent;
import android.view.SurfaceView;

public class InitView extends SurfaceView{

	public InitView(Context context) {
		super(context);
		Intent myIntent = new Intent(getContext(), MenuActivity.class);
		SettingsManager.Instance().getActivity().startActivity(myIntent);  
	}

}
