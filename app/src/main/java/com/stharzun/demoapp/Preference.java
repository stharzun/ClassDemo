package com.stharzun.demoapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    private Context context;
    private static final String fileName = "UserInfo";

    //Constructure
    //public/privite ClassName(){}
    public Preference(Context context) {
        this.context = context;
    }

    //public/privaite return_type method_name(){}
    //method
    public void setData(String name, String email, String username, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
        editor.commit();
    }

    public static String getName(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sharedPreferences.getString("name", "");
    }

    public String getEmail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sharedPreferences.getString("email", "");
    }

    public String getUsername() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sharedPreferences.getString("username", "");
    }

    public String getPassword() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sharedPreferences.getString("password", "");
    }


}
