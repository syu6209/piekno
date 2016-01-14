package com.example.piekno;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.piekno.Utils.DoubleBackCloseHandler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginActivity extends Activity implements OnClickListener {
	Thread th;
	String user_id,user_pw;

	HttpResponse response;
	
	private DoubleBackCloseHandler appcloser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Config.setStatusColor(this, Color.rgb(202, 117, 96));
		setListener();
		appcloser = new DoubleBackCloseHandler(this);
	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();
		try {
			appcloser.onBackPressed();
		} catch (Exception e) {

		}
	}

	public void setListener() {
		ImageButton btn = (ImageButton) findViewById(R.id.btn_login);
		btn.setOnClickListener((android.view.View.OnClickListener) this);
		EditText txt_id = (EditText) findViewById(R.id.login_input_id);
		EditText txt_pw = (EditText) findViewById(R.id.login_input_pw);
		txt_pw.setOnFocusChangeListener(new onFucusChangeHandler());
		
		txt_id.setOnFocusChangeListener(new onFucusChangeHandler());
	}
	
	public class onFucusChangeHandler implements OnFocusChangeListener{
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					v.setBackground(getDrawable(R.drawable.login_textbox_selected));
				} else {
					String tmp = ((EditText) v).getText().toString();
					int len = tmp.length();
					if (len == 0) {
						switch (v.getId()) {
						case R.id.login_input_id:
							v.setBackground(getDrawable(R.drawable.login_textbox_id_normal));
							break;
						case R.id.login_input_pw:
							v.setBackground(getDrawable(R.drawable.login_textbox_pw_normal));
							break;
						default:
							break;
						}
					}else{
						v.setBackground(getDrawable(R.drawable.login_textbox_normal));
					}
				}
			}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.arg1 == 1) {
				startActivity(new Intent(getApplication(), MainActivity.class));
				LoginActivity.this.finish();
				th = null;
			} else {
				// Login Fail
			}
		}
	};

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// startActivity(new Intent(getApplication(),MainActivity.class));
		Config.loadingState = 0;

		EditText txt_id = (EditText)findViewById(R.id.login_input_id);
		EditText txt_pw = (EditText)findViewById(R.id.login_input_pw);
		Intent intent = new Intent(LoginActivity.this, LoadingActivity.class);
		intent.putExtra("Comment", "로그인 중입니다.");
		intent.putExtra("After", "로그인 성공!");
		/*
		intent.putExtra("ID",txt_id.getText().toString());
		intent.putExtra("PW",txt_pw.getText().toString());
		*/
		user_id = txt_id.getText().toString().trim();
		user_pw = txt_pw.getText().toString().trim();
		
		th = new Thread(new Runnable() {

			@Override
			public void run() {
				// Try Login
				try {
					//Thread.sleep(1000);

					//start http connection
					
					HttpPost httppost;
					StringBuffer buffer;
					//HttpResponse response;
					HttpClient httpclient;
					ArrayList<NameValuePair> postdata = new ArrayList<NameValuePair>();
					httpclient = new DefaultHttpClient();
					httppost = new HttpPost(Config.URL_login);
					
					postdata.add(new BasicNameValuePair("user_id", user_id));
					postdata.add(new BasicNameValuePair("user_pw", user_pw));
					
					httppost.setEntity(new UrlEncodedFormEntity(postdata));
					
					response = httpclient.execute(httppost);
					
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					 final String response = httpclient.execute(httppost, responseHandler);
					 
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Config.toast(LoginActivity.this, "로그인 중입니다.");
						}
					});
					//end of http connection
				} catch (Exception e) {

				}
				Config.loadingState = 1;
				Message msg = handler.obtainMessage();

				msg.arg1 = 1;

				handler.sendMessage(msg);

			}
		});

		th.start();

		startActivity(intent);
	}
}