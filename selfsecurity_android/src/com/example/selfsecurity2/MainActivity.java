package com.example.selfsecurity2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;








import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	    private WebView webView;
	  String ITEM_KEY = "key";
	    ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
	    SimpleAdapter adapter;
	    String smsUrl="http://utilities.ociuz.com/sms/sendsms.aspx?userid=[your id]&pwd=[your password]&to=";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.webView1);
		
		startWebView("http://utilities.ociuz.com/securityapp/Default.aspx");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	   public boolean onOptionsItemSelected(MenuItem item) {  
	        switch (item.getItemId()) {  
	            case R.id.item1:  
	             Intent i=new Intent(MainActivity.this,MessageActivity.class); 
	             startActivity(i);
	            return true;
	            default:  
	                return super.onOptionsItemSelected(item);
	        }
			
	   }
	@Override
	  protected void onResume() {
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	  }
	  private void startWebView(String url) {
		    
			//Create new webview Client to show progress dialog
			//When opening a url or click on link
			
			webView.setWebViewClient(new WebViewClient() {      
		        ProgressDialog progressDialog;
		     
		        //If you will not use this method url links are opeen in new brower not in webview
		        public boolean shouldOverrideUrlLoading(WebView view, String url) {              
		            view.loadUrl(url);
		            return true;
		        }
		   
		        //Show loader on url load
		        public void onLoadResource (WebView view, String url) {
		            if (progressDialog == null) {
		                // in standard case YourActivity.this
		                progressDialog = new ProgressDialog(MainActivity.this);
		                progressDialog.setMessage("Loading...");
		                progressDialog.show();
		            }
		        }
		        public void onPageFinished(WebView view, String url) {
		            try{
		            if (progressDialog.isShowing()) {
		                progressDialog.dismiss();
		                progressDialog = null;
		            }
		            }catch(Exception exception){
		                exception.printStackTrace();
		            }
		        }
		        
		    }); 
		     
		     // Javascript inabled on webview  
		    webView.getSettings().setJavaScriptEnabled(true); 
		    
		    // Other webview options
		    /*
		    webView.getSettings().setLoadWithOverviewMode(true);
		    webView.getSettings().setUseWideViewPort(true);
		    webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		    webView.setScrollbarFadingEnabled(false);
		    webView.getSettings().setBuiltInZoomControls(true);
		    */
		    
		    /*
		     String summary = "<html><body>You scored <b>192</b> points.</body></html>";
	         webview.loadData(summary, "text/html", null); 
		     */
		    
		    //Load url in webview
		    webView.loadUrl(url);
		     
		     
		}
		
		// Open previous opened link from history on webview when back button pressed
		
		@Override
	    // Detect when the back button is pressed
	    public void onBackPressed() {
	        if(webView.canGoBack()) {
	            webView.goBack();
	        } else {
	            // Let the system handle the back button
	            super.onBackPressed();
	        }
	    }
}
