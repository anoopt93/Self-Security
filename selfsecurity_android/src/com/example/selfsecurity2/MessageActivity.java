package com.example.selfsecurity2;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends Activity {
EditText edittext;
Button save;
SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		save=(Button)findViewById(R.id.button1);
		sharedpreferences = getSharedPreferences("SMS_MESSAGE", Context.MODE_PRIVATE);	
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				edittext=(EditText)findViewById(R.id.editText1);
				String message=edittext.getText().toString();
				 SharedPreferences.Editor editor = sharedpreferences.edit();
		         editor.putString("MESSAGE", message);
		         editor.commit();
				Toast.makeText(getApplicationContext(),"Message Saved",Toast.LENGTH_LONG).show();
			}


		
		});
		
	}



}
