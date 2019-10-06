package com.ulul.carebuddies.util;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {
    SharedPreferences pref;
    String pref_name;

    public  LocalStorage(Context context, String pref_name){
        this.pref_name = pref_name;
        pref = context.getSharedPreferences(pref_name, context.MODE_PRIVATE);
    }

    public void setString(String key, String value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key){
        return pref.getString(key, null);
    }

    public void setInt(String key, int value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key){
        return pref.getInt(key, 0);
    }
}
