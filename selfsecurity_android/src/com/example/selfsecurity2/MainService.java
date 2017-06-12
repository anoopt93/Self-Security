package com.example.selfsecurity2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONObject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainService extends Service {
	private SensorManager mSensorManager;
	SharedPreferences sharedpreferences;
	private ShakeEventListener mSensorListener;
	String ITEM_KEY = "key";
	SharedPreferences sp;
	ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
	SimpleAdapter adapter;
	String smsUrl = "http://utilities.ociuz.com/sms/sendsms.aspx?userid=hima&pwd=123&to=";
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		 super.onStartCommand(intent, flags, startId);
		Toast.makeText(getApplicationContext(),"Service Started.",Toast.LENGTH_LONG).show();
		
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorListener = new ShakeEventListener();
		try {
			sp= getSharedPreferences("save", Context.MODE_PRIVATE);
			sharedpreferences = getSharedPreferences("SMS_MESSAGE",	Context.MODE_PRIVATE);
		} catch (Exception e1) {
			//Toast.makeText(getApplicationContext(),"Exception"+e1.getMessage(),Toast.LENGTH_LONG).show();
			e1.printStackTrace();
		}
		//Toast.makeText(getApplicationContext(),"setting shaking functionality.",Toast.LENGTH_LONG).show();
		mSensorListener
				.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

					public void onShake() {
						Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
						// Vibrate for 500 milliseconds
						v.vibrate(500);
						System.out.println("Shake........");
						//Toast.makeText(getApplicationContext(), "Shake....!",
								//Toast.LENGTH_SHORT).show();
						try {
							LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
							GPSTracker gps = new GPSTracker(
									getApplicationContext());
							locationManager
									.requestLocationUpdates(
											LocationManager.GPS_PROVIDER, 5000,
											10, gps);// 1000
							// meters
							// (1
							// Km)
							String latitude = String.valueOf(gps.getLatitude());
							String longitude = String.valueOf(gps
									.getLongitude());
							String place = gps.getAddressLine();
							String plac="Place-"+place+" ";

							Calendar cal = Calendar.getInstance();
							String currentDateTime = cal.getTime().toString();
							JSONObject locationObj = new JSONObject();
							locationObj.put("latitude", latitude);
							locationObj.put("longitude", longitude);
							locationObj.put("place", place);
							String message = "latitude-" + latitude
									+ ";longitude-" + longitude + ":place-"
									+ place;
							Toast.makeText(MainService.this, message,
									Toast.LENGTH_LONG).show();
							//String output = new HttpPostClient()
									//.postDataToServer(
										//	"http://192.168.43.128:8061/Selfsecurity/service/webservice/submitLoc","");
							try {
								String ph=sp.getString("phone",null);
								String num="Phone No-"+ph;
								String ur=" http://maps.google.com/?q="+latitude+","+longitude+" ";
								String smsMessage = sharedpreferences
										.getString("MESSAGE", "");
								String sms="Message-"+smsMessage+" ";
								String phoneNos = new HttpPostClient().invokeGet("http://demo.ociuz.com/appService.asmx/onshake?phoneNumber="+ph,"");
								String[] phoneArr = phoneNos.split(",");
								//smsUrl += phoneNos;
								//smsUrl += "&msg="+ph+" "+plac+" "+ur+" "+" "+smsMessage+" ";
								new HttpPostClient().invokeGet(smsUrl, "");
								
								 for(int i=0;i<phoneArr.length;i++){
								  SmsManager smsManager =
								SmsManager.getDefault();
								 smsManager.sendTextMessage(phoneArr[i], null,
								plac+ur+sms, null, null);
								 Toast.makeText(getApplicationContext(),
								 smsMessage+""+phoneArr[i],
								 Toast.LENGTH_LONG).show(); }
								 
							} catch (Exception e) {
								Toast.makeText(getApplicationContext(),
										"SMS faild, please try again.",
										Toast.LENGTH_LONG).show();
								e.printStackTrace();
							}
							//Toast.makeText(MainService.this, output,
								//	Toast.LENGTH_LONG).show();
							gps = null;
							locationManager = null;
						} catch (Exception e) {

						}
					}
				});
		 mSensorManager.registerListener(mSensorListener,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
			        SensorManager.SENSOR_DELAY_UI);
		return START_STICKY;
	}

	

	@Override
	public void onCreate() {
		super.onCreate();
	
	}

}
