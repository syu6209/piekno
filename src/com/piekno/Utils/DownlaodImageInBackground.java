package com.piekno.Utils;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class DownlaodImageInBackground extends AsyncTask<String, Void, Bitmap> {

	ImageView iv;
	public Bitmap bit;

	public DownlaodImageInBackground() {
		iv = null;
	}

	public DownlaodImageInBackground(ImageView iv) {
		this.iv = iv;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		this.bit = result;
		if (iv != null) {
			iv.setImageBitmap(result);
		}
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap bitmap = null;
		try {
			URL url = new URL(params[0]);
			URLConnection conn = url.openConnection();
			conn.connect();

			int nSize = conn.getContentLength();
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream(), nSize);
			bitmap = BitmapFactory.decodeStream(bis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}

}