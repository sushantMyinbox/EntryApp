package com.mim.entryapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static String SHARED_PREF_NAME = "Sushant";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPreferenceManager (Context context){
        this.context = context;
    }

    public void logout(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
