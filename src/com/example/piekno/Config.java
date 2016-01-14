package com.example.piekno;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.WindowManager;
import android.widget.Toast;

public class Config {
	public static String main_banner_img_url[] = new String[5];
	public static int loadingState = 0;
	public static String loadingMove = null;
	public static void toast(Context ctx, String msg){
		try{
		Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT); 
		toast.show();
		}catch(Exception e){
			
		}
	}
	public static void setStatusColor(Activity a, int color){
	      a.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		  a.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		  a.getWindow().setStatusBarColor(color);
	}
}
