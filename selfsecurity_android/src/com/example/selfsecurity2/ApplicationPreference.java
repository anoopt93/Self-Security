package com.example.selfsecurity2;

import android.content.Context;
import android.content.SharedPreferences;

public class ApplicationPreference {
SharedPreferences preferences;
	


	public  ApplicationPreference(Context context) {
		String preference_name = "preference";
		int mode = 0;
		preferences =context.getSharedPreferences(preference_name, mode);
	}

	public void putBoolean(String key, boolean value) {
		preferences.edit().putBoolean(key, value).apply();
	}

	public boolean getBoolean(String key) {
		return preferences.getBoolean(key, false);

	}

	public void putString(String key, String value) {
		preferences.edit().putString(key, value).apply();
	}

	public String getString(String key) {
		return preferences.getString(key, null);

	}

	public void putInteger(String key, int value) {
		preferences.edit().putInt(key, value).apply();
	}

	public int getInteger(String key) {
		return preferences.getInt(key, 0);

	}

}
