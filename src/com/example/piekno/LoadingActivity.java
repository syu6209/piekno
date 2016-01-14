package com.example.piekno;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

public class LoadingActivity extends Activity {
	Thread th;
	String completemsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
		// WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		Config.setStatusColor(this, Color.rgb(102, 153, 255));

		setContentView(R.layout.activity_loading);

		Intent intent = getIntent();
		String comment = (String) intent.getSerializableExtra("Comment");

		completemsg = (String) intent.getSerializableExtra("After");
		if (comment != null || comment.length() > 0) {
			TextView tv = (TextView) this.findViewById(R.id.loading_comment);
			tv.setText(comment);
		}
		init();
	}

	public void init() {
		th = new Thread(new Runnable() {

			@Override
			public void run() {
				while (Config.loadingState == 0) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {

					}
				}
				Config.loadingState = 0;
				Message msg;
				msg = handler.obtainMessage();
				msg.arg1 = 1;
				handler.sendMessage(msg);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {

				}
				Message msg2;
				msg2 = handler.obtainMessage();
				msg2.arg1 = 2;
				handler.sendMessage(msg2);
			}

		});
		th.start();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.arg1 == 1) {
				if (completemsg != null || completemsg.length() > 0) {
					TextView tv = (TextView) findViewById(R.id.loading_comment);
					tv.setText(completemsg);
				}
			} else {
				LoadingActivity.this.finish();
			}
		}
	};
}
