package com.example.testapp.preferance;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.testapp.app.MyApp;

public class PreferenceWrapper {
    private SharedPreferences applicationPreference;
     public PreferenceWrapper() {
        super();
        // TODO Auto-generated constructor stub
        applicationPreference = PreferenceManager
                .getDefaultSharedPreferences(MyApp.getContext());
    }
    public void putStringPref(String key, String value) {
        SharedPreferences.Editor editor = applicationPreference.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public String getStringPref(String key, String defValue) {
        return applicationPreference.getString(key, defValue);

    }
    public void putIntegerPref(String key, int value) {
        SharedPreferences.Editor editor = applicationPreference.edit();
        editor.putInt(key, value);
        editor.commit();
    }
public int getIntegerPref(String key, int defValue) {
        return applicationPreference.getInt(key, defValue);
    }
public void putBooleanPref(String key, boolean value) {
        SharedPreferences.Editor editor = applicationPreference.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
public boolean getBooleanPref(String key, boolean defValue) {
        return applicationPreference.getBoolean(key, defValue);
    }
public void putLongPref(String key, long value) {
        SharedPreferences.Editor editor = applicationPreference.edit();
        editor.putLong(key, value);
        editor.commit();
    }
 public long getLongPref(String key, long defValue) {
        return applicationPreference.getLong(key, defValue);
    }
    public void putFloatPref(String key, float value) {
        SharedPreferences.Editor editor = applicationPreference.edit();
        editor.putFloat(key, value);
        editor.commit();
    } public float getFloatPref(String key, float defValue) {
        return applicationPreference.getFloat(key, defValue);
    }
    public void clearAllPreference() {
        SharedPreferences.Editor editor = applicationPreference.edit();
        editor.clear();
    }


}
