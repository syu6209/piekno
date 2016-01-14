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
			Config.toast(activity, "\'�ڷ�\'��ư�� �ѹ� �� �����ø� ����˴ϴ�.");
			return;
		}
		if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
			activity.finish();
			toast.cancel();
		}
	}
}
