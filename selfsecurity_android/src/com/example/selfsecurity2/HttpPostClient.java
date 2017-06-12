package com.example.selfsecurity2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.StrictMode;
import android.util.Log;

public class HttpPostClient {
	@SuppressLint("NewApi")
	public String postDataToServer(String serviceURL,String Data) {
		// String methodName = params[0].toString();
		// String id = params[1].toString();
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
				new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		StringBuffer sb = new StringBuffer();

		try {
			URL u = new URL(serviceURL);
			HttpResponse response;
			HttpClient client = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(u.toString());

			// pass your data
			StringEntity input = new StringEntity(Data);
			input.setContentType("application/json");
			httppost.setEntity(input);

			response = client.execute(httppost);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = br.readLine();
			sb.append(line);
			// if (methodName.contains("call"))
			// Log.e("status.Call: ", line);
			

			br.close();
			client = null;
			httppost = null;
			response = null;

			String stat = sb.toString();

			if (stat != null)
				if (stat.contains("success")) {

					

				}

		} catch (Exception e) {
			Log.e("error ", e + "");
			return e.getMessage();

		}
		return sb.toString();
	}
	
	@SuppressLint("NewApi")
	public String invokeGet(String serviceURL,String data) {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
				new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		StringBuffer sb = new StringBuffer();

		try {
			URL u = new URL(serviceURL);
			HttpResponse response;
			HttpClient client = new DefaultHttpClient();
			HttpGet httppost = new HttpGet(u.toString());

			// pass your data
			StringEntity input = new StringEntity(data);
			input.setContentType("application/json");

			response = client.execute(httppost);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = br.readLine();
			sb.append(line);
			// if (methodName.contains("call"))
			// Log.e("status.Call: ", line);
			

			br.close();
			client = null;
			httppost = null;
			response = null;

			String status = sb.toString();

			if (status != null)
				if (status.contains("success")) {

					

				}

		} catch (Exception e) {
			Log.e("error ", e + "");
			return e.getMessage();

		}
		return sb.toString();
	}
	
}
