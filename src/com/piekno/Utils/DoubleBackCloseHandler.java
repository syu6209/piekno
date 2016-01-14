package com.piekno.Utils;

import com.example.piekno.Config;

import android.app.Activity;
import android.widget.Toast;

public class DoubleBackCloseHandler {
	private long backKeyPressedTime = 0;
	private Toast toast;

	private Activity activity;

	public DoubleBackCloseHandler(Activity context) {
		this.activity = context;
	}

	public void onBackPressed() throws Exception{
		if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
			backKeyPressedTime = System.currentTimeMillis();
			Config.toast(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.");
			return;
		}
		if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
			activity.finish();
			toast.cancel();
		}
	}
}
