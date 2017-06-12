package com.example.selfsecurity2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends Activity {
	CheckBox remaindUsear;
	EditText txtPhoneno;
	SharedPreferences sp;
	EditText txtPassword;
	public static String keepLogin="keeplogin";
	public static String keepLoginusername="keepLoginusername";
	public static String keepLoginpassword="keepLoginpassword";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sp= getSharedPreferences("save", Context.MODE_PRIVATE);
		txtPhoneno = (EditText) findViewById(R.id.editText1);
		txtPassword = (EditText) findViewById(R.id.editText2);
		remaindUsear = (CheckBox) findViewById(R.id.checkBox1);
		Button btnLogin = (Button) findViewById(R.id.button1);
		checkIskeeploggedin();
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				setkeeploggedin();
				String phoneno=txtPhoneno.getText().toString();
				SharedPreferences.Editor editor = sp.edit();
		         editor.putString("phone",phoneno);
		         editor.commit();
				String password=txtPassword.getText().toString();
				if (phoneno.trim() == ""|| password.trim() == "") {
					AlertDialog.Builder alert = new AlertDialog.Builder(
							LoginActivity.this);

					alert.setTitle("Login");
					alert.setMessage("Enter Phone & Password.?");
					alert.show();
				} else {
					JSONObject values=new JSONObject();
			          try {
						values.put("phoneNumber",txtPhoneno.getText());
						values.put("password",txtPassword.getText());
						
						String status=new HttpPostClient().invokeGet("http://demo.ociuz.com/appService.asmx/authorize?phoneNumber="+phoneno+"&password="+password,"");					
						AlertDialog.Builder alert = new AlertDialog.Builder(
								LoginActivity.this);						
						if(status.equals("success")){
							Intent mainActivity=new Intent(LoginActivity.this,MainActivity.class);
							startActivity(mainActivity);
							startService(new Intent(LoginActivity.this,  MainService.class));  
							//alert.setTitle("Login Success");
							//alert.setMessage("Welcome");
						}else{
							alert.setTitle("Login Failed");
							alert.setMessage("Try Again");
							alert.show();
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					/*String returnString = executeUrl("http://192.168.0.100/eBanking/BankApp/appLogin.aspx?username="
							+ txtPhoneno.getText()
							+ "&password="
							+ txtPassword.getText());*/
				}
			}

			private void setkeeploggedin() {
				if (remaindUsear.isChecked() == true) {
					ApplicationPreference applicationPreference = new ApplicationPreference(LoginActivity.this);
					applicationPreference.putBoolean(keepLogin, true);
					applicationPreference.putString(keepLoginusername,txtPhoneno.getText().toString());
					applicationPreference.putString(keepLoginpassword,txtPassword.getText().toString());
				}
			}
		});
	}
	private void checkIskeeploggedin() {
		ApplicationPreference applicationPreference = new ApplicationPreference(LoginActivity.this);
		boolean isCheckIskeeploggedin=applicationPreference.getBoolean(keepLogin);
		if ( isCheckIskeeploggedin== true) {
			
			txtPhoneno.setText(applicationPreference.getString(
					keepLoginusername));
			txtPassword.setText(applicationPreference.getString(
					keepLoginpassword));
			remaindUsear.setChecked(true);
		}
		
	}
	@SuppressLint("NewApi")
	public String executeUrl(String url) {

		String returnString = "";
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
				new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		try {
			URL u = new URL(url);
			HttpResponse response;
			HttpClient client = new DefaultHttpClient();
			// HttpGet httppost = new HttpGet(u.toString());
			HttpPost httppost = new HttpPost(u.toString());

			response = client.execute(httppost);

			StringBuffer sb = new StringBuffer();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				Log.e("line", line);
				line = br.readLine();

			}

			// jObject = new JSONObject(sb.toString());
			returnString = sb.toString();
		}

		catch (Exception e) {
			Log.e("error ", e + "");
			return null;
		}

		return returnString;
	}
}
