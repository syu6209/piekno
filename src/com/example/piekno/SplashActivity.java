package com.example.piekno;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Handler hd = new Handler();
		
		Config.setStatusColor(this,Color.WHITE);
		getandsetvalue();
		
		hd.postDelayed(new SplashHandler(), 1500);
	}
	public void getandsetvalue(){
		Config.main_banner_img_url[0] = "http://www.danvely.com/image/pageimg/ib_img476img_xlMOZ03DG7.jpg";
		Config.main_banner_img_url[1] = "http://www.danvely.com/upload/151207114334event_open.png";
		Config.main_banner_img_url[2] = "http://danvely.com/image/commuboard/15/151116114847postfiles2.naver.net.png";
		Config.main_banner_img_url[3] = "http://danvely.com/image/commuboard/15/151116114846postfiles16.naver.net.png";
		Config.main_banner_img_url[4] = "http://danvely.com/image/commuboard/15/151116105844FF.png";
	}
	private class SplashHandler implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			startActivity(new Intent(getApplication(),LoginActivity.class));
			SplashActivity.this.finish();
		}
		
	}
}
